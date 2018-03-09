package com.lntinfotech.design.patterns.creational.builder;

public class Employee {

	public static class Builder {
		private String firstName;
		private String lastName;
		private String gender;
		private String id;
		
		public Builder(){
			
		}
		
		public Employee build(){
			return new Employee(this);
		}
		
		public Builder firstName(String firstName){
			this.firstName=firstName;
			return this;
		}
		
		public Builder lastName(String lastName){
			this.lastName=lastName;
			return this;
		}
		
		public Builder gender(String gender){
			this.gender=gender;
			return this;
		}
		
		public Builder id(String id){
			this.id=id;
			return this;
		}
		
	}
	private final String firstName;
	private final String lastName;
	private final String gender;
	private final String id;
	
	private Employee(Builder builder){
		this.firstName = builder.firstName;
		this.lastName = builder.lastName; 
		this.gender = builder.gender;
		this.id = builder.id ;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getGender() {
		return gender;
	}
	public String getId() {
		return id;
	}
	
	
	
}
