package com.student.spring.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class StudentSpringApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentSpringApiRestApplication.class, args);
	}

	@RestController
	class HelloController {
		@GetMapping("/")
		String hello() {
			return "Hello from App Engine !";
		}
	}
	
	

}
