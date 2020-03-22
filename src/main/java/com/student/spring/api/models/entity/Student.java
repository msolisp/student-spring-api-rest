package com.student.spring.api.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "student")
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

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

	@NotNull(message = "cannot be empty")
	private int age;

	@ManyToOne
	@JoinColumn(name = "course_code")
	private Course course;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(@NotEmpty(message = "cannot be empty") String rut,
			@NotEmpty(message = "cannot be empty") String name, @NotEmpty(message = "cannot be empty") String lastName,
			@NotEmpty(message = "cannot be empty") @Min(value = 18, message = "Age should not be less than 18") @Max(value = 150, message = "Age should not be greater than 150") int age,
			Course course) {
		super();
		this.rut = rut;
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.course = course;
	}

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

}
