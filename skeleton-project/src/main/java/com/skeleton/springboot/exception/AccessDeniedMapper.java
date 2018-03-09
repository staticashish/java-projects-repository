package com.skeleton.springboot.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.springframework.security.access.AccessDeniedException;

public class AccessDeniedMapper implements
		ExceptionMapper<AccessDeniedException> {

	@Override
	public Response toResponse(AccessDeniedException accessDeniedException) {
		// TODO Auto-generated method stub
		return Response.status(401)
                .build();
	}

}
