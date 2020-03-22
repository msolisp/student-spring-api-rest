package com.student.spring.api.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.spring.api.models.entity.Student;

public interface IStudentDao extends JpaRepository<Student, String> {

}
