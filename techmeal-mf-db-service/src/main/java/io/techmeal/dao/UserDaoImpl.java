package io.techmeal.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.techmeal.domain.User;
import io.techmeal.repository.UserRepository;

@Component
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public void delete(User user) {
		userRepository.save(user);
	}
}
