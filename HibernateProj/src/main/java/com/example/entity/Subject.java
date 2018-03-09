package com.example.entity;

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
@Table(name="SUBJECT")
public class Subject {

	@OneToMany(mappedBy="id.subject",cascade=CascadeType.ALL)
	private List<StudentSubjectMapping> studentSubjectMappings;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SUBJECT_ID")
	private Long subjectId;
	
	@Column(name="SUBJECT_NAME")
	private String subjectName;
	
	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public List<StudentSubjectMapping> getStudentSubjectMappings() {
		return studentSubjectMappings;
	}

	public void setStudentSubjectMappings(
			List<StudentSubjectMapping> studentSubjectMappings) {
		this.studentSubjectMappings = studentSubjectMappings;
	}

}
