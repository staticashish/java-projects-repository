package com.skeleton.springboot.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.skeleton.springboot.entity.Role;

@SuppressWarnings("serial")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RoleDto implements Serializable{

	private Long id;
	private String name;
	
	public RoleDto(Role role) {
		this.id = role.getId();
		this.name = role.getName();
	}
	
	public RoleDto() {
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
}
