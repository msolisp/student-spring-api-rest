package com.student.spring.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.spring.api.models.entity.Course;
import com.student.spring.api.models.services.ICourseService;

@RestController
@RequestMapping("/api")
public class CourseRestController {

	@Autowired
	private ICourseService courseService;

	@GetMapping("/courses/all")
	public List<Course> index() {
		return courseService.findAll();
	}

	@GetMapping("/courses/page/{page}")
	public Page<Course> page(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 5);
		return courseService.findAll(pageable);
	}

	@GetMapping("/courses/{code}")
	public ResponseEntity<?> show(@PathVariable String code) {

		Course course = null;
		Map<String, Object> response = new HashMap<>();

		try {
			course = courseService.findById(code);
		} catch (DataAccessException e) {
			response.put("message", "Error when querying the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (course == null) {
			response.put("message", "The course code: ".concat(code.concat(" does not exist in the database !")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}

	// OK
	@PostMapping("/courses")
	public ResponseEntity<?> create(@RequestBody Course course) {

		Course newCourse = null;
		Map<String, Object> response = new HashMap<>();

		try {
			newCourse = courseService.save(course);

		} catch (DataAccessException e) {

			response.put("message", "error while inserting into the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "The course has been successfully created");
		response.put("course", newCourse);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/courses/{code}")
	public ResponseEntity<?> update(@RequestBody Course course, @PathVariable String code) {

		Course currentCourse = courseService.findById(code);

		Course updatedCourse = null;

		Map<String, Object> response = new HashMap<>();

		if (currentCourse == null) {
			response.put("message", "Error: could not edit, the course code: "
					.concat(code.concat(" does not exist in the database !")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			currentCourse.setName(course.getName());
			updatedCourse = courseService.save(currentCourse);

		} catch (DataAccessException e) {

			response.put("message", "Failed to update the course in the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("message", "The course has been successfully updated");
		response.put("course", updatedCourse);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@DeleteMapping("/courses/{code}")
	public ResponseEntity<?> delete(@PathVariable String code) {

		Map<String, Object> response = new HashMap<>();

		try {

			courseService.delete(code);

		} catch (DataAccessException e) {

			response.put("message", "Failed to delete course in database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "The course was successfully removed");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
