package com.skeleton.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExeptionHandler {

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ExceptionResponse> handleApplicationException(ApplicationException e){

		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setErrorId(e.getErrorId());
		exceptionResponse.setErrorMessage(e.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.valueOf(e.getErrorId()));		
	}
}
