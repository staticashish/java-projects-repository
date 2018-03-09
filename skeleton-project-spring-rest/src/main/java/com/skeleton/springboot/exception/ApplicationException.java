package com.skeleton.springboot.exception;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ApplicationException extends Exception implements Serializable {
	
	private int errorId;
	
	public int getErrorId() {
		return errorId;
	}
	public void setErrorId(int errorId) {
		this.errorId = errorId;
	}
	
	public ApplicationException() {
        super();
    }
	public ApplicationException(String msg, int errorId) {
        super(msg);
        this.errorId = errorId;
    }
 
    public ApplicationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
