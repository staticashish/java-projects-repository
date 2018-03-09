package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="STUDENT")
public class Student {

	@OneToMany(mappedBy="id.student",cascade=CascadeType.ALL)
	private List<StudentSubjectMapping> studentSubjectMappings = new ArrayList<StudentSubjectMapping>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="STUDENT_ID")
	private Long studentId;
	
	@Column(name="STUDENT_NAME")
	private String studentName;
	

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public List<StudentSubjectMapping> getStudentSubjectMappings() {
		return studentSubjectMappings;
	}

	public void setStudentSubjectMappings(
			List<StudentSubjectMapping> studentSubjectMappings) {
		this.studentSubjectMappings = studentSubjectMappings;
	}

	 public void addUserGroup(StudentSubjectMapping studentSubjectMapping) {
	        this.studentSubjectMappings.add(studentSubjectMapping);
	    }

}
