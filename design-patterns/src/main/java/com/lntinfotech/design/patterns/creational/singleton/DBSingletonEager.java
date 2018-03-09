package com.lntinfotech.design.patterns.creational.singleton;

public class DBSingletonEager {

	//Eager initialization
	private static DBSingletonEager instance = new DBSingletonEager();
	
	private DBSingletonEager(){
		
	}
	
	public static DBSingletonEager getInstance(){
		return instance;
	}
}
