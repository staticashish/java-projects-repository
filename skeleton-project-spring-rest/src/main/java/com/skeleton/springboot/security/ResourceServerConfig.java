package com.skeleton.springboot.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//@Configuration
//@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
				.and()
				.csrf()
				.disable()
				.authorizeRequests()
				.antMatchers("/js/**", "/", "/index.html", "/home.html",
						"/login.html", "/registerUser.html",
						"/api/user/create", "/api/role/getallroles", "api/user/authenticate")
				.permitAll()
				.anyRequest()
				.fullyAuthenticated()
				.and()
				.formLogin()
				.failureUrl("/login")
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout.html"))
				.logoutSuccessUrl("/login.html");
		
        http
        .addFilterAfter(new WebSecurityFilter(), UsernamePasswordAuthenticationFilter.class);

	}
}
