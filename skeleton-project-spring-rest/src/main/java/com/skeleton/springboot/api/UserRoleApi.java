package com.skeleton.springboot.api;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skeleton.springboot.dto.RoleRequestDto;
import com.skeleton.springboot.dto.RoleResponseDto;
import com.skeleton.springboot.exception.ApplicationException;

@RestController
@RequestMapping("role")
public interface UserRoleApi {

	@GetMapping(path = "/getallroles", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<RoleResponseDto> getAllRoles();

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RolesAllowed("ROLE_ADMIN")
	ResponseEntity<RoleResponseDto> createRoles(
			@RequestBody RoleRequestDto roleRequestDto)
			throws ApplicationException;
}
