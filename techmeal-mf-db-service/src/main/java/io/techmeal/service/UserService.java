package io.techmeal.service;

import java.util.List;

import io.techmeal.rest.V1.dto.UserDto;

public interface UserService {

	UserDto getUser(String username);

	UserDto createUser(UserDto user);

	List<UserDto> getAllUsers();

	UserDto editUser(UserDto userDto);

	void deleteUser(UserDto userDto);

}