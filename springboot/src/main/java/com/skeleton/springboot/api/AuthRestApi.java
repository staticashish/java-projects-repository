package com.skeleton.springboot.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skeleton.springboot.dto.TokenResponseDto;
import com.skeleton.springboot.dto.UserRequestDto;
import com.skeleton.springboot.exception.ApplicationException;

@RestController
public interface AuthRestApi {

	@PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TokenResponseDto> authenticate(
			@RequestBody UserRequestDto userRequestDto)
			throws ApplicationException;
}
