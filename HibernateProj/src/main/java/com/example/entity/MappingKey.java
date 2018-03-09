package com.example.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Embeddable
public class MappingKey implements Serializable{
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Student student;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Subject subject;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
}
