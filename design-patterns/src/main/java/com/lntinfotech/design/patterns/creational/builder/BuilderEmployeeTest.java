package com.lntinfotech.design.patterns.creational.builder;

public class BuilderEmployeeTest {

	public static void main(String[] args) {

		Employee.Builder builder = new Employee.Builder();
		Employee employee = builder.firstName("Ashish")
									.lastName("Sonawane")
									.id("10637062")
									.gender("Male")
									.build();
		
		System.out.println(employee.getFirstName());
		System.out.println(employee.getLastName());
		System.out.println(employee.getId());
		System.out.println(employee.getGender());
	}

}
