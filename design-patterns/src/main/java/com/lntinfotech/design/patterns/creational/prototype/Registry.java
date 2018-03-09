package com.lntinfotech.design.patterns.creational.prototype;

import java.util.HashMap;
import java.util.Map;

public class Registry {

	private Map<String, Item> items = new HashMap<String, Item>();
	
	public Registry() {
		loadItems();
	}
	
	public Item createItem(String type){
		Item item = null;
		
		try {
			item = (Item)(items.get(type)).clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return item;
	}

	private void loadItems() {

		Movie movie = new Movie();
		movie.setTitle("First Movie");
		movie.setPrice(199.00);
		movie.setRuntime("2 hrs");
		
		items.put("Movie", movie);
		
		Book book = new Book();
		book.setTitle("First Book");
		book.setPrice(20.00);
		book.setNumberofPages(200);
		
		items.put("Book", book);
	}		

}
