package io.techmeal.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.techmeal.dao.RoleDao;
import io.techmeal.dao.UserDao;
import io.techmeal.domain.Role;
import io.techmeal.domain.User;
import io.techmeal.exception.type.EntityExistException;
import io.techmeal.exception.type.EntityNotFoundException;
import io.techmeal.rest.V1.dto.UserDto;
import io.techmeal.util.ApplicationModelMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private ApplicationModelMapper applicationModelMapper;
	
	@Override
	public UserDto getUser(String username) {
		List<User> users = userDao.getUserByUsername(username);
		if(users.isEmpty()) {
			throw new EntityNotFoundException("user does not exist with username : [ "+username+" ]");
		}
		UserDto userDto = users.stream()
								.findFirst()
								.map(u -> applicationModelMapper.convertUserToUserDto(u))
								.get();
		
		return userDto;
	}
	
	@Override
	public UserDto createUser(UserDto userDto) {

		List<User> users = userDao.getUserByUsername(userDto.getUsername());
		if(users.isEmpty()) {
			User user = applicationModelMapper.convertUserDtoToUser(userDto);
			if(userDto.getRoles() == null || userDto.getRoles().isEmpty()) {
				Role userRole = roleDao.getRoleByName("ROLE_USER");
				if(userRole == null) throw new EntityNotFoundException("role ROLE_USER does not exist while creating user with username : [ "+ userDto.getUsername()+" ]");
				Set<Role> roleSet = new HashSet<Role>();
				roleSet.add(userRole);
				user.setRoles(roleSet);	
			}
			User savedUser = userDao.save(user);
			return applicationModelMapper.convertUserToUserDto(savedUser);
		} else {
			throw new EntityExistException("user already exist with username : [ "+userDto.getUsername()+" ]");
		}
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userDao.getAllUsers();
		
		List<UserDto> userDtos = users.stream()
				.map(u -> applicationModelMapper.convertUserToUserDto(u))
				.collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public UserDto editUser(UserDto userDto) {
		List<User> users = userDao.getUserByUsername(userDto.getUsername());
		if(!users.isEmpty()){
			User editedUser = applicationModelMapper.convertUserDtoToUser(userDto);
			User user = userDao.save(editedUser);
			return applicationModelMapper.convertUserToUserDto(user);
		} else {
			throw new EntityExistException("user does not exist with username : [ "+userDto.getUsername()+" ]");
		}
	}

	@Override
	public void deleteUser(UserDto userDto) {
		List<User> users = userDao.getUserByUsername(userDto.getUsername());
		for(User user: users){
			userDao.delete(user);
		}
	}
	
}
