package com.lntinfotech.design.patterns.creational.factory;

public class FactoryDemoTest {

	public static void main(String[] args) {
		Website site = WebsiteFactory.getWebSite(WebsiteType.BLOG);
		System.out.println(site.getPages());
		
		site = WebsiteFactory.getWebSite(WebsiteType.SHOP);
		System.out.println(site.getPages());
	}

}
