package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Employee {

	@Id
	@Column(name = "EMP_ID")
	@GeneratedValue(strategy=GenerationType.TABLE) //This is important! 
	private Long empId;
	
	@Column(name= "EMP_NAME")
	private String empName;

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
}
