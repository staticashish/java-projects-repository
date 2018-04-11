package io.techmeal.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.techmeal.domain.Role;
import io.techmeal.repository.RoleRepository;

@Component
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role getRoleByName(String roleName){
		return roleRepository.findByName(roleName);
	}
	
	@Override
	public Role save(Role role)	{
		return roleRepository.save(role);
	}
	
	@Override
	public void delete(Role role)	{
		roleRepository.delete(role);
	}
	
	@Override
	public List<Role> getAllRoles()	{
		return (List<Role>) roleRepository.findAll();
	}
	
}
