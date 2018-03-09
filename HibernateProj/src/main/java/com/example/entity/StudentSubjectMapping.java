package com.example.entity;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="STUDENT_SUBJECT_MAPPING")
@AssociationOverrides({
    @AssociationOverride(name = "id.student",
        joinColumns = @JoinColumn(name = "STUDENT_ID_FK")),
    @AssociationOverride(name = "id.subject",
        joinColumns = @JoinColumn(name = "SUBJECT_ID_FK")) })
public class StudentSubjectMapping {

	@EmbeddedId
	private MappingKey id = new MappingKey();
	
	@Column(name="STUDENT_MARKS")
	private Long marks;
	
	@Transient
	private Student student;
	
	@Transient
	private Subject subject;

	public MappingKey getId() {
		return id;
	}

	public void setId(MappingKey id) {
		this.id = id;
	}

	public Long getMarks() {
		return marks;
	}

	public void setMarks(Long marks) {
		this.marks = marks;
	}

	public Student getStudent() {
		return getId().getStudent();
	}

	public void setStudent(Student student) {
		getId().setStudent(student);
	}

	public Subject getSubject() {
		return getId().getSubject();
	}

	public void setSubject(Subject subject) {
		getId().setSubject(subject);
	}
	
	
}
