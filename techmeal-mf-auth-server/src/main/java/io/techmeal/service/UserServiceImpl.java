package io.techmeal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.techmeal.dao.UserDao;
import io.techmeal.domain.User;
import io.techmeal.rest.V1.dto.UserDto;
import io.techmeal.util.ApplicationModelMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ApplicationModelMapper applicationModelMapper;
	
	@Override
	public UserDto getUser(String username) {
		List<User> users = userDao.getUserByUsername(username);
		if(users.isEmpty()) {
			throw new RuntimeException("user does not exist with username : [ "+username+" ]");
		}
		UserDto userDto = users.stream()
								.findFirst()
								.map(u -> applicationModelMapper.convertUserToUserDto(u))
								.get();
		
		return userDto;
	}
}
