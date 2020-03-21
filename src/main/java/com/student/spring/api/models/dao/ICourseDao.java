package com.student.spring.api.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.spring.api.models.entity.Course;

public interface ICourseDao extends JpaRepository<Course, String> {

}
