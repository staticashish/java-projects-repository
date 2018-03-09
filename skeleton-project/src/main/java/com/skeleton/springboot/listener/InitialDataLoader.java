package com.skeleton.springboot.listener;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.skeleton.springboot.entity.Role;
import com.skeleton.springboot.entity.User;
import com.skeleton.springboot.repository.RoleRepository;
import com.skeleton.springboot.repository.UserRepository;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {
 
    boolean alreadySetup = false;
 
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;
        /*Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);*/        
        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");
 
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        Set<Role> roleSet = new HashSet<Role>();
        roleSet.add(adminRole);
        User user = userRepository.findOneByUsername("admin");
        if(user == null)
        {
        	user = new User();
        	user.setUsername("admin");
        	user.setPassword("admin");
        	user.setFirstName("admin");
        	user.setLastName("admin");
        	user.setRoles(roleSet);
        	userRepository.save(user);
        }
        alreadySetup = true;
    }

    @Transactional
    private Role createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }
}
