package com.example.test;


import java.util.Locale;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.bean.spring.AbstractVehicle;
import com.example.bean.spring.Car;
import com.example.bean.spring.Vehicle;

public class SpringCoreTest {

	@SuppressWarnings({ "deprecation", "resource" })
	public static void main(String[] args) {
		
		final Locale localeFr = new Locale("fr", "FR");
		AbstractApplicationContext  context = new ClassPathXmlApplicationContext("Spring.xml");
		//ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		Vehicle vehicle = (Vehicle) context.getBean("vehicle");
		Vehicle vehicle2 = (Vehicle) context.getBean("vehicle");
		//System.out.println(vehicle1.equals(vehicle2));
		System.out.println(vehicle);
		vehicle.printVehicleName("Honda-City");
		System.out.println(vehicle2.getCar().equals(vehicle.getCar()));
		System.out.println(vehicle.getCar());
		System.out.println("======================");
		
		AbstractVehicle abstractVehicle = (AbstractVehicle) context.getBean("abstractVehicle");
		Car car1 = abstractVehicle.createCar();
		Car car2 = abstractVehicle.createCar();
		
		System.out.println(car1.equals(car2));
		
		System.out.println(context.getMessage("welcome.message",new Object[]{"Ashish"},Locale.US));
		System.out.println(context.getMessage("welcome.message",new Object[]{"Ashish"},localeFr));
		
		context.close();
	}

}
