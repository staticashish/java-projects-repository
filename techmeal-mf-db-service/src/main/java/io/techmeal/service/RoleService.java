package io.techmeal.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import io.techmeal.rest.V1.dto.RoleDto;

@Service
public interface RoleService {

	Set<RoleDto> getAllRoles();

	RoleDto createRole(RoleDto roleDto);
	
}
