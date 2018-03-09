package com.example.bean.spring;

import org.springframework.stereotype.Component;

@Component
public class Car {

	public double carNo; 
	
	public Car() {
		System.out.println("Constructor in Car");
	}

	public void setCarName(String name){
		System.out.println("Car is :"+name);
	}
	
	public double getCarNo() {
		return carNo;
	}

	public void setCarNo(double carNo) {
		this.carNo = carNo;
	}

	@Override
	public String toString() {
		return "Car [carNo=" + carNo + "]";
	}
}
