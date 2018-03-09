package com.skeleton.springboot.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skeleton.springboot.dao.CategoryDao;
import com.skeleton.springboot.dto.CategoryDto;
import com.skeleton.springboot.dto.CategoryRequestDto;
import com.skeleton.springboot.dto.CategoryResponseDto;
import com.skeleton.springboot.entity.Category;
import com.skeleton.springboot.exception.ApplicationException;

@RestController(value = "category")
public class CategoryApiImpl implements CategoryApi {

	private static final Logger LOG = LoggerFactory
			.getLogger(CategoryApiImpl.class);

	@Autowired
	CategoryDao categoryDao;

	@Override
	public ResponseEntity<CategoryResponseDto> createCategory(
			@RequestBody CategoryRequestDto categoryRequestDto)
			throws ApplicationException {

		List<Category> categories = new ArrayList<Category>();
		for (CategoryDto categoryDto : categoryRequestDto.getCategoryDtos()) {
			Category category = categoryDao.findByName(categoryDto.getName());
			if (category == null) {
				category = new Category();
				category.setName(categoryDto.getName());
				categoryDao.saveCategory(category);
				categories.add(category);
			} else {
				throw new ApplicationException("category already exists", HttpStatus.CONFLICT.value());
			}
		}

		List<CategoryDto> categoryDtos = StreamSupport
				.stream(categories.spliterator(), false).map(CategoryDto::new)
				.collect(Collectors.toList());

		CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
		categoryResponseDto.setCategoryDtos(categoryDtos);
		return new ResponseEntity<CategoryResponseDto>(categoryResponseDto,
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CategoryResponseDto> getCategories() {
		List<Category> categories = (List<Category>) categoryDao
				.getAllCategories();
		List<CategoryDto> categoryDtos = StreamSupport
				.stream(categories.spliterator(), false).map(CategoryDto::new)
				.collect(Collectors.toList());

		CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
		categoryResponseDto.setCategoryDtos(categoryDtos);
		return new ResponseEntity<CategoryResponseDto>(categoryResponseDto,
				HttpStatus.OK);
	}
}
