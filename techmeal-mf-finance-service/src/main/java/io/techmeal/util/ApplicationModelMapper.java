package io.techmeal.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.techmeal.rest.V1.dto.DataResponseDto;


@Component
public class ApplicationModelMapper {

	@Autowired
	private ModelMapper mapper;
	
	public DataResponseDto convertStringToDataResponseData(String response){
		System.out.println(response);
		return mapper.map(response, DataResponseDto.class);
	}
	

}
