package com.lntinfotech.design.patterns.creational.singleton;

public class DBSingletonThreadSafe {

	//lazy initialization
	private static DBSingletonThreadSafe instance = null;
	
	private DBSingletonThreadSafe(){
		
	}
	
	public static DBSingletonThreadSafe getInstance(){
		if(instance == null){
			synchronized (DBSingletonThreadSafe.class) {
				if(instance == null){
					instance = new DBSingletonThreadSafe();
				}
			}
			
		}
		return instance;
	}
}
