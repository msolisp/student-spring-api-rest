package com.student.spring.api.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "student")
public class Student implements Serializable {

	/**
	 * 
	 */
	@Id
	@NotEmpty(message = "cannot be empty")
	@Column(nullable = false, unique = true)
	private String rut;

	@NotEmpty(message = "cannot be empty")
	@Column(nullable = false)
	private String name;

	@NotEmpty(message = "cannot be empty")
	@Column(nullable = false)
	private String lastName;

	@NotEmpty(message = "cannot be empty")
	@Column(nullable = false, unique = true)
	@Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
	private int age;

	@ManyToOne(fetch = FetchType.LAZY)
	@Size(min = 1, max = 4, message = "must be between 1 to 4 characters")
	private Course course;

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	private static final long serialVersionUID = 1L;

}
