package com.skeleton.springboot.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.skeleton.springboot.api.UserApiImpl;

public class WebSecurityFilter extends OncePerRequestFilter {

	private static final Logger LOG = LoggerFactory
			.getLogger(WebSecurityFilter.class);

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	UserDetailsService userDetailsService;

	@Value("${jwt.header}")
	private String tokenHeader;

	@Override
	protected void initFilterBean() throws ServletException {
		super.initFilterBean();
		 
	}
	
	@Override
	public void destroy() {
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			System.out.println("---> "+this.tokenHeader);
			String authToken = request.getHeader("Authorization");
			// authToken.startsWith("Bearer ")
			// String authToken = header.substring(7);
			if(tokenUtils == null && userDetailsService == null){
			    ServletContext servletContext = request.getServletContext();
			    WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			    tokenUtils = webApplicationContext.getBean(TokenUtils.class);
			    userDetailsService = webApplicationContext.getBean(UserDetailsService.class);
			}
			String username = tokenUtils.getUsernameFromToken(authToken);

			LOG.info("checking authentication für user " + username);

			if (username != null
					&& SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = this.userDetailsService
						.loadUserByUsername(username);
				if (tokenUtils.validateToken(authToken, userDetails)) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource()
							.buildDetails(request));
					LOG.info("authenticated user " + username
							+ ", setting security context");
					SecurityContextHolder.getContext().setAuthentication(
							authentication);
				}
			}

			filterChain.doFilter(request, response);
		} catch (Exception e) {
			LOG.info("exception occurs {}",e.getMessage());
			e.printStackTrace();
		}
	}

}
