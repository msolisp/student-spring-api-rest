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

import com.student.spring.api.models.entity.Student;
import com.student.spring.api.models.services.IStudentService;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	@Autowired
	private IStudentService studentService;

	@GetMapping("/students/all")
	public List<Student> index() {
		return studentService.findAll();
	}

	@GetMapping("/students/page/{page}")
	public Page<Student> page(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 5);
		return studentService.findAll(pageable);
	}

	@GetMapping("/students/{rut}")
	public ResponseEntity<?> show(@PathVariable String rut) {

		Student student = null;
		Map<String, Object> response = new HashMap<>();

		try {
			student = studentService.findById(rut);
		} catch (DataAccessException e) {
			response.put("message", "Error when querying the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (student == null) {
			response.put("message", "The student rut: ".concat(rut.concat(" does not exist in the database !")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

	@PostMapping("/students")
	public ResponseEntity<?> create(@RequestBody Student student) {

		Student newStudent = null;
		Map<String, Object> response = new HashMap<>();

		try {
			newStudent = studentService.save(student);

		} catch (DataAccessException e) {

			response.put("message", "error while inserting into the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "The student has been successfully created");
		response.put("course", newStudent);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/students/{rut}")
	public ResponseEntity<?> update(@RequestBody Student student, @PathVariable String rut) {

		Student currentStudent = studentService.findById(rut);

		Student updatedStudent = null;

		Map<String, Object> response = new HashMap<>();

		if (currentStudent == null) {
			response.put("message",
					"Error: could not edit, the student rut: ".concat(rut.concat(" does not exist in the database !")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			currentStudent.setName(student.getName());
			updatedStudent = studentService.save(currentStudent);

		} catch (DataAccessException e) {

			response.put("message", "Failed to update the course in the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("message", "The student has been successfully updated");
		response.put("course", updatedStudent);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@DeleteMapping("/students/{rut}")
	public ResponseEntity<?> delete(@PathVariable String rut) {

		Map<String, Object> response = new HashMap<>();

		try {

			studentService.delete(rut);

		} catch (DataAccessException e) {

			response.put("message", "Failed to delete student in database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "The student was successfully removed");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
