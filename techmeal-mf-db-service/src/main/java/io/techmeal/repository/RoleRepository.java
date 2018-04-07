package io.techmeal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.techmeal.domain.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	
	Role findByName(String name);
}
