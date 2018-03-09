package com.skeleton.springboot.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skeleton.springboot.entity.User;
import com.skeleton.springboot.repository.UserRepository;

@Component
public class UserDao {

	private static final Logger LOG = LoggerFactory.getLogger(UserDao.class);

	@Autowired
	UserRepository userRepository;

	public User findByUsername(String username){
		User user = userRepository.findOneByUsername(username);
		return user;
	}

	public User saveUser(User user){
		return userRepository.save(user);
	}

	public void deleteUser(User user){
		userRepository.delete(user);
	}

	public List<User> getAllUsers(){
		List<User> users = (List<User>) userRepository.findAll();
		return users;
	}
}
