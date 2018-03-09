package com.skeleton.springboot.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.skeleton.springboot.entity.Category;

@SuppressWarnings("serial")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryDto implements Serializable {

	private Long id;
	private String name;
	
	public CategoryDto(Category category) {
		this.id = category.getId();
		this.name = category.getName();
	}
	
	public CategoryDto() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "CategoryDto [id=" + id + ", name=" + name + "]";
	}
	
	
}
