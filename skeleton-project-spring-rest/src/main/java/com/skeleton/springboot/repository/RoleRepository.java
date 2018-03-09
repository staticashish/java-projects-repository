package com.skeleton.springboot.repository;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;

import com.skeleton.springboot.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
	Role findByName(String string);

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
	List<Role> findAll();
}
