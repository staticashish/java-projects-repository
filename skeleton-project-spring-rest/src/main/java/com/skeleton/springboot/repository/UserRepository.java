package com.skeleton.springboot.repository;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;

import com.skeleton.springboot.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
	Page<User> findAll(Pageable pageable);

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
	User findOneByUsername(String username); 

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
	List<User> findAll();
}
