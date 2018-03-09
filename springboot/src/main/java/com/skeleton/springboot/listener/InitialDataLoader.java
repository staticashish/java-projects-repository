package com.skeleton.springboot.listener;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.skeleton.springboot.dao.SequenceDao;
import com.skeleton.springboot.entity.Role;
import com.skeleton.springboot.entity.User;
import com.skeleton.springboot.entity.type.DocumentKey;
import com.skeleton.springboot.repository.RoleRepository;
import com.skeleton.springboot.repository.UserRepository;

@Component
public class InitialDataLoader implements
		ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private SequenceDao sequenceDao;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		try {
			if (alreadySetup)
				return;

			createRoleIfNotFound("ROLE_ADMIN");
			createRoleIfNotFound("ROLE_USER");

			Role adminRole = roleRepository.findByName("ROLE_ADMIN");
			Set<Role> roleSet = new HashSet<Role>();
			roleSet.add(adminRole);
			User user = userRepository.findOneByUsername("admin");
			if (user == null) {
				user = new User();
				user.setId(sequenceDao.getNextSequenceId(DocumentKey.USER
						.getKey()));
				user.setUsername("admin");
				user.setPassword("admin");
				user.setFirstName("admin");
				user.setLastName("admin");
				user.setRoles(roleSet);
				userRepository.save(user);
			}
			alreadySetup = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	private Role createRoleIfNotFound(String name) throws Exception {
		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role(name);
			role.setId(sequenceDao.getNextSequenceId(DocumentKey.ROLE.getKey()));
			roleRepository.save(role);
		}
		return role;
	}
}
