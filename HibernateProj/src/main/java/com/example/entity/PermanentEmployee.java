package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PERMANENT_EMPLOYEE")
public class PermanentEmployee extends Employee {

	@Column(name="SALARY")
	private Double Salary;
	
	@Column(name="ICARD_COLOR")
	private String iCardColor;

	public Double getSalary() {
		return Salary;
	}

	public void setSalary(Double salary) {
		Salary = salary;
	}

	public String getiCardColor() {
		return iCardColor;
	}

	public void setiCardColor(String iCardColor) {
		this.iCardColor = iCardColor;
	}

	
}
