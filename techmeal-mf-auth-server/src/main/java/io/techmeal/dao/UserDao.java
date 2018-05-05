package io.techmeal.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import io.techmeal.domain.User;

@Component
public interface UserDao {

	List<User> getUserByUsername(String username);
}