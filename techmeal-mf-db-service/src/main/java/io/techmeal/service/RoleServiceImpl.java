package io.techmeal.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.techmeal.dao.RoleDao;
import io.techmeal.domain.Role;
import io.techmeal.exception.type.EntityExistException;
import io.techmeal.rest.V1.dto.RoleDto;
import io.techmeal.util.ApplicationModelMapper;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao; 
	
	@Autowired
	private ApplicationModelMapper applicationModelMapper; 
	
	@Override
	public Set<RoleDto> getAllRoles() {
		Set<Role> roles = new HashSet<Role>(roleDao.getAllRoles());
		Set<RoleDto> rolesDto = roles.stream()
			.map(role -> applicationModelMapper.convertRoleToRoleDto(role))
			.collect(Collectors.toSet());
		return rolesDto;
	}

	@Override
	public RoleDto createRole(RoleDto roleDto) {
		Role role = roleDao.getRoleByName(roleDto.getName());
		if(role == null) {
			role = applicationModelMapper.convertRoleDtoToRole(roleDto);
			Role savedRole = roleDao.save(role);
			return applicationModelMapper.convertRoleToRoleDto(savedRole);
		}else {
			throw new EntityExistException("role already exist with name : [ "+roleDto.getName()+" ]");
		}
	}
}
