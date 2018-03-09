package com.skeleton.springboot.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final Logger LOG = LoggerFactory
			.getLogger(RestAuthenticationEntryPoint.class);
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		LOG.info("exception {}",exception.getMessage());
//		if(!response.isCommitted()) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
//		}
	}

}
