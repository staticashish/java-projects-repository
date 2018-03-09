package com.skeleton.springboot.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryRequestDto implements Serializable {

	List<CategoryDto> categoryDtos;

	public List<CategoryDto> getCategoryDtos() {
		return categoryDtos;
	}

	public void setCategoryDtos(List<CategoryDto> categoryDtos) {
		this.categoryDtos = categoryDtos;
	}

	@Override
	public String toString() {
		return "CategoryRequestDto [categoryDtos=" + categoryDtos + "]";
	}
	
}
