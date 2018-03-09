package com.skeleton.springboot.api;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skeleton.springboot.dto.UserDto;
import com.skeleton.springboot.dto.UserRequestDto;
import com.skeleton.springboot.dto.UserResponseDto;
import com.skeleton.springboot.exception.ApplicationException;

@RestController
@RequestMapping("user")
public interface UserApi {

	@GetMapping
	ResponseEntity<UserDto> getUser();

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<UserResponseDto> createUsers(@RequestBody UserRequestDto userRequestDto)
			throws ApplicationException;

	@PostMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<UserResponseDto> updateUsers(@RequestBody UserRequestDto userRequestDto)
			throws ApplicationException;

	@GetMapping(path = "/getusers", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<UserResponseDto> getUsers();

	@RolesAllowed("ROLE_ADMIN")
	@PutMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteUser(@RequestBody UserRequestDto userRequestDto)
			throws ApplicationException;

}
