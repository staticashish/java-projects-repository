package com.skeleton.springboot.entity.type;

public enum DocumentKey {
	ROLE("role"), USER("user"), CATEGORY("category");
	
	private String key;

	private DocumentKey(String key) {
		this.key = key;
	}
	
	public String getKey(){
		return this.key;
	}
}
