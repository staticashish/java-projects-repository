package com.skeleton.springboot.repository;

import java.util.List;

//import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;

import com.skeleton.springboot.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	Category findByName(String string);
	List<Category> findAll();
}
