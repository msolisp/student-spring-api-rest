package com.student.spring.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

}
