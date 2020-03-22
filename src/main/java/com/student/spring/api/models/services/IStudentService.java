package com.student.spring.api.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.student.spring.api.models.entity.Student;

public interface IStudentService {
	
	public List<Student> findAll();

	public Page<Student> findAll(Pageable pageable);

	public Student findById(String rut);

	public Student save(Student student);

	public void delete(String rut);

	public boolean existsById(String rut);
	
	

}
