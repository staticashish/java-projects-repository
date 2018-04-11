package io.techmeal.dao;

import java.util.Collection;

import org.springframework.stereotype.Component;

import io.techmeal.domain.Role;

@Component
public interface RoleDao {

	Role getRoleByName(String roleName);

	Role save(Role role);

	void delete(Role role);

	Collection<? extends Role> getAllRoles();

}