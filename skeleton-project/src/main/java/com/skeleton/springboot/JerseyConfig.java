package com.skeleton.springboot;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.springframework.context.annotation.Configuration;

import com.skeleton.springboot.api.UserApi;
import com.skeleton.springboot.api.UserRoleApi;
import com.skeleton.springboot.exception.ApplicationExceptionHandler;
import com.skeleton.springboot.security.WebSecurityFilter;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig{
	public JerseyConfig() {
		//packages("com.skeleton.springboot");
		register(UserApi.class);
		register(UserRoleApi.class);
		register(WebSecurityFilter.class);
		register(LoggingFilter.class);
		register(ApplicationExceptionHandler.class);
		register(RolesAllowedDynamicFeature.class);
	}
}
