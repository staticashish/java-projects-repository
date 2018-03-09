package com.skeleton.springboot.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skeleton.springboot.dto.CategoryRequestDto;
import com.skeleton.springboot.dto.CategoryResponseDto;
import com.skeleton.springboot.exception.ApplicationException;

@RestController
@RequestMapping("category")
public interface CategoryApi {

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<CategoryResponseDto> createCategory(
			@RequestBody CategoryRequestDto categoryRequestDto)
			throws ApplicationException;

	@GetMapping(path = "/getcategories", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<CategoryResponseDto> getCategories()
			throws ApplicationException;

}
