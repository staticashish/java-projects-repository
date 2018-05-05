package io.techmeal.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.techmeal.domain.User;
import io.techmeal.rest.V1.dto.UserDto;

@Component
public class ApplicationModelMapper {

	@Autowired
	private ModelMapper mapper;
	
	public UserDto convertUserToUserDto(User user){
		return mapper.map(user, UserDto.class);
	}
	
	public User convertUserDtoToUser(UserDto userDto){
		return mapper.map(userDto, User.class);
	}
}
