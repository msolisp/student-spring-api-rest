package com.student.spring.api.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.spring.api.models.dao.IStudentDao;
import com.student.spring.api.models.entity.Student;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private IStudentDao studentDao;

	@Override
	@Transactional(readOnly = true)
	public List<Student> findAll() {
		return (List<Student>) studentDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Student> findAll(Pageable pageable) {
		return studentDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Student findById(String rut) {
		return studentDao.findById(rut).orElse(null);
	}

	@Override
	@Transactional
	public Student save(Student student) {
		return studentDao.save(student);
	}

	@Override
	@Transactional
	public void delete(String rut) {
		studentDao.deleteById(rut);
	}

	@Override
	@Transactional
	public boolean existsById(String rut) {
		Boolean exits;
		exits = studentDao.existsById(rut);
		return exits;
	}

}
