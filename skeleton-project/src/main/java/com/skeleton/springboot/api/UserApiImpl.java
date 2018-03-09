package com.skeleton.springboot.api;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.skeleton.springboot.dao.RoleDao;
import com.skeleton.springboot.dao.UserDao;
import com.skeleton.springboot.dto.TokenResponseDto;
import com.skeleton.springboot.dto.UserDto;
import com.skeleton.springboot.dto.UserRequestDto;
import com.skeleton.springboot.dto.UserResponseDto;
import com.skeleton.springboot.entity.Role;
import com.skeleton.springboot.entity.User;
import com.skeleton.springboot.exception.ApplicationException;
import com.skeleton.springboot.repository.RoleRepository;
import com.skeleton.springboot.repository.UserRepository;
import com.skeleton.springboot.security.TokenUtils;
import com.skeleton.springboot.service.CustomUserDetailsService;

@Controller
public class UserApiImpl implements UserApi {

	private static final Logger LOG = LoggerFactory
			.getLogger(UserApiImpl.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	private CustomUserDetailsService userService;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authManager;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private TokenUtils tokenUtils;

	@Override
	public Response createUsers(UserRequestDto userRequestDto)
			throws ApplicationException {
		List<User> users = new ArrayList<User>();
		for (UserDto userDto : userRequestDto.getUserDtos()) {
			if (userDao.findByUsername(userDto.getUsername()) == null) {
				User user = new User();
				Role userRole = roleDao.findByName("ROLE_USER");
				Set<Role> roleSet = new HashSet<Role>();
				roleSet.add(userRole);
				user.setUsername(userDto.getUsername());
				user.setPassword(userDto.getPassword());
				user.setFirstName(userDto.getFirstName());
				user.setLastName(userDto.getLastName());
				user.setEmail(userDto.getEmail());
				user.setRoles(roleSet);
				User savedUser = userDao.saveUser(user);
				users.add(savedUser);
			} else {
				throw new ApplicationException("Username already exists", 1000);
			}
		}

		List<UserDto> resultUserDtos = StreamSupport
				.stream(users.spliterator(), false).map(UserDto::new)
				.collect(Collectors.toList());

		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setUserDtos(resultUserDtos);
		return Response.status(HttpStatus.SC_OK).entity(userResponseDto)
				.build();
	}

	@Override
	public Response updateUsers(UserRequestDto userRequestDto)
			throws ApplicationException {
		List<User> users = new ArrayList<User>();
		for (UserDto userDto : userRequestDto.getUserDtos()) {
			User user = userDao.findByUsername(userDto.getUsername());
			if (user != null) {
				user.setPassword(userDto.getPassword());
				user.setFirstName(userDto.getFirstName());
				user.setLastName(userDto.getLastName());
				user.setEmail(userDto.getEmail());
				user.setRoles(userDto.getRoles());
				User savedUser = userDao.saveUser(user);
				users.add(savedUser);
			} else {
				throw new ApplicationException("User does not exists", 1001);
			}
		}
		List<UserDto> resultUserDtos = StreamSupport
				.stream(users.spliterator(), false).map(UserDto::new)
				.collect(Collectors.toList());

		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setUserDtos(resultUserDtos);
		return Response.status(HttpStatus.SC_OK).entity(userResponseDto)
				.build();

	}

	@Override
	public Response getUsers() {
		List<User> users = (List<User>) userDao.getAllUsers();
		List<UserDto> resultUserDtos = StreamSupport
				.stream(users.spliterator(), false).map(UserDto::new)
				.collect(Collectors.toList());

		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setUserDtos(resultUserDtos);
		return Response.status(HttpStatus.SC_OK).entity(userResponseDto)
				.build();
	}

	@Override
	public Response getUser() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof String
				&& ((String) principal).equals("anonymousUser")) {
			throw new WebApplicationException(401);
		}
		UserDetails userDetails = (UserDetails) principal;
		User user = userDao.findByUsername(userDetails.getUsername());
		UserDto userDto = new UserDto(user);
		return Response.status(HttpStatus.SC_OK).entity(userDto).build();
	}

	@Override
	public Response authenticate(UserRequestDto userRequestDto)
			throws ApplicationException {
		String username = userRequestDto.getUserDtos().stream().findFirst()
				.get().getUsername();
		String password = userRequestDto.getUserDtos().stream().findFirst()
				.get().getPassword();
		String role = userRequestDto.getUserDtos().stream().findFirst().get()
				.getRoles().stream().findFirst().get().getName();
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); 
		authorities.add(new SimpleGrantedAuthority(role));
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				username, password, authorities);
		Authentication authentication = authManager
				.authenticate(usernamePasswordAuthenticationToken);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = this.userService.loadUserByUsername(username);
		TokenResponseDto tokenResponseDto = new TokenResponseDto(
				tokenUtils.generateToken(userDetails));
		return Response.status(HttpStatus.SC_OK).entity(tokenResponseDto)
				.build();
	}

	@Override
	public Response deleteUser(UserRequestDto userRequestDto)
			throws ApplicationException {
		for (UserDto userDto : userRequestDto.getUserDtos()) {
			User user = userDao.findByUsername(userDto.getUsername());
			if (user != null) {
				userDao.deleteUser(user);
			} else {
				throw new ApplicationException("User does not exists", 1001);
			}
		}

		return Response.status(HttpStatus.SC_OK).build();
	}
}
