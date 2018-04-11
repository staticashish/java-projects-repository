package io.techmeal.service;

import io.techmeal.rest.V1.dto.UserDto;

public interface UserService {

	UserDto getUser(String username);

	UserDto createUser(UserDto user);

}