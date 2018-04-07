package io.techmeal.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.techmeal.domain.MutualFund;
import io.techmeal.domain.Role;
import io.techmeal.domain.User;
import io.techmeal.rest.V1.dto.MutualFundDto;
import io.techmeal.rest.V1.dto.RoleDto;
import io.techmeal.rest.V1.dto.UserDto;

@Component
public class ApplicationModelMapper {

	@Autowired
	private ModelMapper mapper;
	
	public UserDto convertUserToUserDto(User user){
		return mapper.map(user, UserDto.class);
	}
	
	public User convertUserDtoToUser(UserDto userDto){
		return mapper.map(userDto, User.class);
	}
	
	public RoleDto convertRoleToRoleDto(Role role){
		return mapper.map(role, RoleDto.class);
	}
	
	public Role convertRoleDtoToRole(RoleDto roleDto){
		return mapper.map(roleDto, Role.class);
	}
	
	public MutualFundDto convertMutualFundToMutualFundDto(MutualFund mutualFund){
		return mapper.map(mutualFund, MutualFundDto.class);
	}
	
	public MutualFund convertMutualFundDtoToMutualFund(MutualFundDto mutualFundDto){
		return mapper.map(mutualFundDto, MutualFund.class);
	}
}
