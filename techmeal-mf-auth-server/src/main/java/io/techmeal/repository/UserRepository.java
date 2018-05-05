package io.techmeal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.techmeal.domain.User;
import java.lang.String;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findByUsername(String username);
}
