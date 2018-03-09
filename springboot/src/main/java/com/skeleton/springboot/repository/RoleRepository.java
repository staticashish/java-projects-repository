package com.skeleton.springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.skeleton.springboot.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

	Role findByName(String string);
	List<Role> findAll();
}
