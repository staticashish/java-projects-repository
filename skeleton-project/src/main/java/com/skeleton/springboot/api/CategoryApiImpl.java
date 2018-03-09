package com.skeleton.springboot.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.skeleton.springboot.dao.CategoryDao;
import com.skeleton.springboot.dto.CategoryDto;
import com.skeleton.springboot.dto.CategoryRequestDto;
import com.skeleton.springboot.dto.CategoryResponseDto;
import com.skeleton.springboot.dto.UserDto;
import com.skeleton.springboot.dto.UserResponseDto;
import com.skeleton.springboot.entity.Category;
import com.skeleton.springboot.entity.User;
import com.skeleton.springboot.exception.ApplicationException;

@Controller
public class CategoryApiImpl implements CategoryApi {

	private static final Logger LOG = LoggerFactory.getLogger(CategoryApiImpl.class);
	
	@Autowired
	CategoryDao categoryDao;
	
	@Override
	public Response createCategory(CategoryRequestDto categoryRequestDto)
			throws ApplicationException {
		
		List<Category> categories = new ArrayList<Category>();
		for (CategoryDto categoryDto : categoryRequestDto.getCategoryDtos()){
			Category category = categoryDao.findByName(categoryDto.getName());
			if(category == null){
				category = new Category();
				category.setName(categoryDto.getName());
				categoryDao.saveCategory(category);
				categories.add(category);
			}else{
				throw new ApplicationException("category already exists",1002);
			}
		}

		List<CategoryDto> categoryDtos = 
				StreamSupport.stream(categories.spliterator(), false)
				.map(CategoryDto::new)
				.collect(Collectors.toList());

		CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
		categoryResponseDto.setCategoryDtos(categoryDtos);
		return Response.status(HttpStatus.SC_OK).entity(categoryResponseDto).build();

	}
	
	@Override
	public Response getCategories() {
		List<Category> categories = (List<Category>) categoryDao.getAllCategories();
		List<CategoryDto> categoryDtos = 
				StreamSupport.stream(categories.spliterator(), false)
				.map(CategoryDto::new)
				.collect(Collectors.toList());

		CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
		categoryResponseDto.setCategoryDtos(categoryDtos);
		return Response.status(HttpStatus.SC_OK).entity(categoryResponseDto).build();
	}

}
