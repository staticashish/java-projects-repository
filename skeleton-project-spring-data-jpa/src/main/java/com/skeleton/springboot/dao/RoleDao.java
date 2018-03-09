package com.skeleton.springboot.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skeleton.springboot.entity.Role;
import com.skeleton.springboot.repository.RoleRepository;

@Component
public class RoleDao {

	private static final Logger LOG = LoggerFactory.getLogger(RoleDao.class);

	@Autowired
	RoleRepository roleRepository;

	public Role findByName(String roleName){
		Role role = roleRepository.findByName(roleName); 
		return role;
	}

	public List<Role> getAllRoles(){
		List<Role> roles = (List<Role>) roleRepository.findAll();
		return roles;
	}

	public Role saveRole(Role role){
		return roleRepository.save(role);
	}
}
