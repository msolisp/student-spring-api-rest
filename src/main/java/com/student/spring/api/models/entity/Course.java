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

	@Id
	@NotEmpty(message = "cannot be empty")
	@Size(min = 1, max = 4, message = "must be between 1 to 4 characters")
	@Column(nullable = false, unique = true)
	private String code;

	@NotEmpty(message = "cannot be empty")
	@Size(min = 1, max = 255, message = "Must be between 1 to 255 characters")
	@Column(nullable = false)
	private String name;

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

	private static final long serialVersionUID = 1L;

}
