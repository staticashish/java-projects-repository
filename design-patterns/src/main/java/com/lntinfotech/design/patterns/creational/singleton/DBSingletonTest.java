package com.lntinfotech.design.patterns.creational.singleton;

public class DBSingletonTest {

	public static void main(String[] args) {

		//Eager Initialization
		DBSingletonEager instanceEager = DBSingletonEager.getInstance();
		System.out.println(instanceEager);
		DBSingletonEager anotherInstanceEager = DBSingletonEager.getInstance();
		System.out.println(anotherInstanceEager);
		System.out.println("=======================");
		
		//Lazy Initialization
		DBSingletonLazy instanceLazy = DBSingletonLazy.getInstance();
		System.out.println(instanceLazy);
		DBSingletonLazy anotherInstanceLazy = DBSingletonLazy.getInstance();
		System.out.println(anotherInstanceLazy);
		System.out.println("=======================");
		
		DBSingletonThreadSafe instanceThreadSafe = DBSingletonThreadSafe.getInstance();
		System.out.println(instanceThreadSafe);
		DBSingletonThreadSafe anotherInstanceThreadSafe = DBSingletonThreadSafe.getInstance();
		System.out.println(anotherInstanceThreadSafe);
		System.out.println("=======================");
	}

}
