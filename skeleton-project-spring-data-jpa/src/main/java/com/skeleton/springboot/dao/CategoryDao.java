package com.skeleton.springboot.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skeleton.springboot.entity.Category;
import com.skeleton.springboot.repository.CategoryRepository;

@Component
public class CategoryDao {

	private static final Logger LOG = LoggerFactory.getLogger(CategoryDao.class);

	@Autowired
	CategoryRepository categoryRepository;

	public Category findByName(String roleName){
		Category category = categoryRepository.findByName(roleName); 
		return category;
	}

	public List<Category> getAllCategories(){
		List<Category> categories = (List<Category>) categoryRepository.findAll();
		return categories;
	}

	public Category saveCategory(Category category){
		return categoryRepository.save(category);
	}
}
