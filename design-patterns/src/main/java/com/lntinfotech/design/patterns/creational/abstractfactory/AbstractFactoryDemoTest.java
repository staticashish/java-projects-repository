package com.lntinfotech.design.patterns.creational.abstractfactory;

public class AbstractFactoryDemoTest {

	public static void main(String[] args) {

		EmployeeFactory employeeFactoryP = EmployeeFactory.getEmployeeFactory(EmployeeType.P);
		Employee employeeGradeA = employeeFactoryP.getEmployee(GradeType.A);
		
		System.out.println(employeeFactoryP);

		EmployeeFactory employeeFactoryV = EmployeeFactory.getEmployeeFactory(EmployeeType.V);
		employeeGradeA = employeeFactoryV.getEmployee(GradeType.A);
		
		System.out.println(employeeFactoryV);
		
	}

}
