package io.techmeal;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import io.techmeal.exception.type.EntityNotFoundException;
import io.techmeal.rest.V1.dto.RoleDto;
import io.techmeal.rest.V1.dto.UserDto;
import io.techmeal.service.RoleService;
import io.techmeal.service.UserService;

@Component
public class ContextRefreshedListner implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		try {
			userService.getUser("admin");
		} catch (EntityNotFoundException e) {
			RoleDto adminRoleDto = new RoleDto.Builder()
										.name("ROLE_ADMIN")
										.build();
			adminRoleDto = roleService.createRole(adminRoleDto);
			Set<RoleDto> adminRoles = new HashSet<>();
			adminRoles.add(adminRoleDto);
			UserDto adminUserDto = new UserDto.Builder()
										.name("Admin")
										.username("admin")
										.password("admin")
										.email("admin@techmeal.io")
										.roles(adminRoles)
										.build();
			adminUserDto = userService.createUser(adminUserDto);
		}

	}

}
