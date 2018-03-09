package com.lntinfotech.design.patterns.creational.factory;


import java.util.ArrayList;
import java.util.List;

public abstract class Website {
	
	protected List<Page> pages = new ArrayList<Page>();
	
	public List<Page> getPages(){
		return this.pages;
	}
	
	public Website(){
		this.creatWebSite();
	}

	abstract void creatWebSite(); 
}
