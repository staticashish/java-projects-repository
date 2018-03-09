package com.skeleton.springboot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.skeleton.springboot.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Page<User> findAll(Pageable pageable);
	User findOneByUsername(String username); 
	List<User> findAll();
}
