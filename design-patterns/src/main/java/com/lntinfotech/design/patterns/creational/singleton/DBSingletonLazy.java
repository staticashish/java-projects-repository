package com.lntinfotech.design.patterns.creational.singleton;

public class DBSingletonLazy {

	//lazy initialization
	private static DBSingletonLazy instance = null;
	
	private DBSingletonLazy(){
		
	}
	
	public static DBSingletonLazy getInstance(){
		if(instance == null){
			instance = new DBSingletonLazy();
		}
		return instance;
	}
}
