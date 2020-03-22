package com.student.spring.api.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.spring.api.models.dao.ICourseDao;
import com.student.spring.api.models.entity.Course;

@Service
public class CourseServiceImpl<E> implements ICourseService {

	@Autowired
	private ICourseDao courseDao;

	@Override
	@Transactional(readOnly = true)
	public List<Course> findAll() {
		return (List<Course>) courseDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Course> findAll(Pageable pageable) {
		return courseDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Course findById(String code) {
		return courseDao.findById(code).orElse(null);
	}

	@Override
	@Transactional
	public Course save(Course course) {
		return courseDao.save(course);
	}

	@Override
	@Transactional
	public void delete(String code) {
		courseDao.deleteById(code);

	}

	@Override
	@Transactional
	public boolean existsById(String code) {
		Boolean exits;
		exits = courseDao.existsById(code);
		return exits;
	}

}
