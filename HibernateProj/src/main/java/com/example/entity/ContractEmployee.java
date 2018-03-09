package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CONTRACT_EMPLOYEE")
public class ContractEmployee extends Employee {

	@Column(name="PER_DAY")
	private Double perDay;
	
	@Column(name="GRADE_BAND")
	private String gradeBand;

	public Double getPerDay() {
		return perDay;
	}

	public void setPerDay(Double perDay) {
		this.perDay = perDay;
	}

	public String getGradeBand() {
		return gradeBand;
	}

	public void setGradeBand(String gradeBand) {
		this.gradeBand = gradeBand;
	}

	

}
