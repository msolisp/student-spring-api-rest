package com.student.spring.api.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "course")
public class Course implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotEmpty(message = "cannot be empty")
	@Column(length = 4, nullable = false, unique = true)
	private String code;

	@NotEmpty(message = "cannot be empty")
	@Size(min = 1, max = 255, message = "Must be between 1 to 255 characters")
	@Column(nullable = false)
	private String name;

	public Course() {
		super();
	}

	public Course(@NotEmpty(message = "cannot be empty") String code,
			@NotEmpty(message = "cannot be empty") @Size(min = 1, max = 255, message = "Must be between 1 to 255 characters") String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
