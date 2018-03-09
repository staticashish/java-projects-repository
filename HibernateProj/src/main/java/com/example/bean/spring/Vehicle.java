package com.example.bean.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;


public class Vehicle implements InitializingBean,DisposableBean{

	private Car car;
	
	public Vehicle(){
		System.out.println("No-arg constructor");
	}
	
	public Vehicle(Car car){
		System.out.println("Constructor with car");
		this.car=car;
	}

	public void printVehicleName(String name){
		car.setCarName(name);
	}
	
	@Override
	public String toString() {
		return "Vehicle [car=" + car + "]";
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		System.out.println("Setter with car");
		this.car = car;
	}
	
	public void init(){
		System.out.println("Vehicle init method..");
	}
	
	public void destroy(){
		System.out.println("Vehicle destroy method..");
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("Vehicle afterPropertiesSet");
		
	}
}
