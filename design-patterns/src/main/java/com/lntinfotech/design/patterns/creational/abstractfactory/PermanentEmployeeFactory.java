package com.lntinfotech.design.patterns.creational.abstractfactory;

public class PermanentEmployeeFactory extends EmployeeFactory {

	@Override
	public Employee getEmployee(GradeType gradeType) {
		
		switch (gradeType) {
			case A:
				return new GradeAEmployee();
			case B:	
				return new GradeBEmployee();
			default:
				return null;
		}
	}

	
}
