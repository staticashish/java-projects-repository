package com.skeleton.springboot.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skeleton.springboot.dto.TokenResponseDto;
import com.skeleton.springboot.dto.UserRequestDto;
import com.skeleton.springboot.exception.ApplicationException;
import com.skeleton.springboot.security.TokenUtils;
import com.skeleton.springboot.service.CustomUserDetailsService;

@RestController
public class AuthRestApiImpl implements AuthRestApi {

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private CustomUserDetailsService userService;

	
	@Override
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public Response authenticate(@RequestBody UserRequestDto userRequestDto)
			throws ApplicationException {
		String username = userRequestDto.getUserDtos().stream().findFirst()
				.get().getUsername();
		String password = userRequestDto.getUserDtos().stream().findFirst()
				.get().getPassword();
		String role = userRequestDto.getUserDtos().stream().findFirst().get()
				.getRoles().stream().findFirst().get().getName();
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); 
		authorities.add(new SimpleGrantedAuthority(role));
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				username, password, authorities);
		Authentication authentication = authManager
				.authenticate(usernamePasswordAuthenticationToken);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = this.userService.loadUserByUsername(username);
		TokenResponseDto tokenResponseDto = new TokenResponseDto(
				tokenUtils.generateToken(userDetails));
		return Response.status(HttpStatus.SC_OK).entity(tokenResponseDto)
				.build();
	}

}
