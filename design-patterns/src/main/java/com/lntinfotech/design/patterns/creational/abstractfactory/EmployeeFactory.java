package com.lntinfotech.design.patterns.creational.abstractfactory;

public abstract class EmployeeFactory {

	public static EmployeeFactory getEmployeeFactory(EmployeeType employeeType){
		
		switch (employeeType) {
		case P:
			return new PermanentEmployeeFactory();
			
		case V:
			return new VendorEmployeeFactory();
			
		default:
			return null;
		}
	}
	
	public abstract Employee getEmployee(GradeType gradeType);
}
