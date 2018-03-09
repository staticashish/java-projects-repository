package com.skeleton.springboot.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ApplicationExceptionHandler implements
		ExceptionMapper<ApplicationException> {

	@Override
	public Response toResponse(ApplicationException exception) {
		ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorId(exception.getErrorId());
        errorResponse.setErrorMessage(exception.getMessage());
        return Response.status(Status.CONFLICT).entity(
        		errorResponse).type(
                MediaType.APPLICATION_JSON).build();
	}

}
