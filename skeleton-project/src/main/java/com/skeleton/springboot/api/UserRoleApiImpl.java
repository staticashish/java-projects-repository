package com.skeleton.springboot.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.skeleton.springboot.dao.RoleDao;
import com.skeleton.springboot.dto.RoleDto;
import com.skeleton.springboot.dto.RoleRequestDto;
import com.skeleton.springboot.dto.RoleResponseDto;
import com.skeleton.springboot.entity.Role;
import com.skeleton.springboot.exception.ApplicationException;

@Controller
public class UserRoleApiImpl implements UserRoleApi {

	private static final Logger LOG = LoggerFactory.getLogger(UserRoleApiImpl.class);

	@Autowired
	private RoleDao roleDao;

	@Override
	public Response getAllRoles() {
		List<Role> roles = roleDao.getAllRoles();
		List<RoleDto> resultRoleDtos = 
				StreamSupport.stream(roles.spliterator(), false)
				.map(RoleDto::new)
				.collect(Collectors.toList());

		return Response.status(HttpStatus.SC_OK).entity(resultRoleDtos).build();
	}

	@Override
	public Response createRoles(RoleRequestDto roleRequestDto)
			throws ApplicationException {
		List<Role> roles = new ArrayList<Role>();
		for (RoleDto roleDto : roleRequestDto.getRoleDtos()){
			Role role = roleDao.findByName(roleDto.getName());
			if(role == null){
				role = new Role();
				role.setName(roleDto.getName());
				roleDao.saveRole(role);
				roles.add(role);
			}else{
				throw new ApplicationException("role already exists",1002);
			}
		}

		List<RoleDto> roleUserDtos = 
				StreamSupport.stream(roles.spliterator(), false)
				.map(RoleDto::new)
				.collect(Collectors.toList());

		RoleResponseDto roleResponseDto = new RoleResponseDto();
		roleResponseDto.setRoleDtos(roleUserDtos);
		return Response.status(HttpStatus.SC_OK).entity(roleResponseDto).build();
	}
}
