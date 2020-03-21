package com.student.spring.api.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.student.spring.api.models.entity.Course;

public interface ICourseService {

	public List<Course> findAll();

	public Page<Course> findAll(Pageable pageable);

	public Course findById(String code);

	public Course save(Course course);

	public void delete(String code);

	public boolean existsById(String code);

}
