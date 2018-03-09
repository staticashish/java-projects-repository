package com.skeleton.springboot.exception;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ExceptionResponse implements Serializable {

	private int errorId;
	private String errorMessage;

	public int getErrorId() {
		return errorId;
	}
	
	public void setErrorId(int errorId) {
		this.errorId = errorId;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
