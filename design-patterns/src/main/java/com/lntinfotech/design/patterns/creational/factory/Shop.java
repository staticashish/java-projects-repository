package com.lntinfotech.design.patterns.creational.factory;

public class Shop extends Website {

	@Override
	void creatWebSite() {
		pages.add(new CartPage());
		pages.add(new ItemPage());
		pages.add(new SearchPage());
		
	}

}
