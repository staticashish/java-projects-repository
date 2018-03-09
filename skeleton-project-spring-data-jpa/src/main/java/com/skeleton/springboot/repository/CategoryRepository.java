package com.skeleton.springboot.repository;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;

import com.skeleton.springboot.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
	Category findByName(String string);

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
	List<Category> findAll();
}
