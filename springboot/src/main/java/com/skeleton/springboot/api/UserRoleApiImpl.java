package com.skeleton.springboot.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skeleton.springboot.dao.RoleDao;
import com.skeleton.springboot.dao.SequenceDao;
import com.skeleton.springboot.dto.RoleDto;
import com.skeleton.springboot.dto.RoleRequestDto;
import com.skeleton.springboot.dto.RoleResponseDto;
import com.skeleton.springboot.entity.Role;
import com.skeleton.springboot.entity.type.DocumentKey;
import com.skeleton.springboot.exception.ApplicationException;

@RestController(value = "role")
public class UserRoleApiImpl implements UserRoleApi {

	private static final Logger LOG = LoggerFactory
			.getLogger(UserRoleApiImpl.class);

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private SequenceDao sequenceDao;

	@Override
	public ResponseEntity<RoleResponseDto> getAllRoles() {
		List<Role> roles = roleDao.getAllRoles();
		List<RoleDto> resultRoleDtos = StreamSupport
				.stream(roles.spliterator(), false).map(RoleDto::new)
				.collect(Collectors.toList());
		RoleResponseDto roleResponseDto = new RoleResponseDto();
		roleResponseDto.setRoleDtos(resultRoleDtos);
		return new ResponseEntity<RoleResponseDto>(roleResponseDto,
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RoleResponseDto> createRoles(
			@RequestBody RoleRequestDto roleRequestDto)
			throws ApplicationException {
		List<Role> roles = new ArrayList<Role>();
		for (RoleDto roleDto : roleRequestDto.getRoleDtos()) {
			Role role = roleDao.findByName(roleDto.getName());
			if (role == null) {
				role = new Role();
				role.setId(sequenceDao.getNextSequenceId(DocumentKey.ROLE.getKey()));
				role.setName(roleDto.getName());
				roleDao.saveRole(role);
				roles.add(role);
			} else {
				throw new ApplicationException("role already exists", HttpStatus.CONFLICT.value());
			}
		}

		List<RoleDto> roleUserDtos = StreamSupport
				.stream(roles.spliterator(), false).map(RoleDto::new)
				.collect(Collectors.toList());

		RoleResponseDto roleResponseDto = new RoleResponseDto();
		roleResponseDto.setRoleDtos(roleUserDtos);
		return new ResponseEntity<RoleResponseDto>(roleResponseDto,
				HttpStatus.OK);
	}
}
