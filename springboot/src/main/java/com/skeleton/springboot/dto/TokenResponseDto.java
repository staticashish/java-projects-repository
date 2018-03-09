package com.skeleton.springboot.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TokenResponseDto implements Serializable {

	private String token;
	
	public TokenResponseDto(String token){
		this.token=token;
	}

	public String getToken() {
		return token;
	}
}