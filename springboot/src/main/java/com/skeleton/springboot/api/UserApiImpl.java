package com.skeleton.springboot.api;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skeleton.springboot.dao.RoleDao;
import com.skeleton.springboot.dao.SequenceDao;
import com.skeleton.springboot.dao.UserDao;
import com.skeleton.springboot.dto.UserDto;
import com.skeleton.springboot.dto.UserRequestDto;
import com.skeleton.springboot.dto.UserResponseDto;
import com.skeleton.springboot.entity.Role;
import com.skeleton.springboot.entity.User;
import com.skeleton.springboot.entity.type.DocumentKey;
import com.skeleton.springboot.exception.ApplicationException;
import com.skeleton.springboot.repository.RoleRepository;
import com.skeleton.springboot.repository.UserRepository;
import com.skeleton.springboot.security.TokenUtils;
import com.skeleton.springboot.service.CustomUserDetailsService;

@RestController(value = "user")
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

	@Autowired
	private SequenceDao sequenceDao;
	
	@Override
	public ResponseEntity<UserResponseDto> createUsers(
			@RequestBody UserRequestDto userRequestDto)
			throws ApplicationException {
		List<User> users = new ArrayList<User>();
		for (UserDto userDto : userRequestDto.getUserDtos()) {
			if (userDao.findByUsername(userDto.getUsername()) == null) {
				User user = new User();
				user.setId(sequenceDao.getNextSequenceId(DocumentKey.USER.getKey()));
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
				throw new ApplicationException("Username already exists", HttpStatus.CONFLICT.value());
			}
		}

		List<UserDto> resultUserDtos = StreamSupport
				.stream(users.spliterator(), false).map(UserDto::new)
				.collect(Collectors.toList());

		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setUserDtos(resultUserDtos);
		return new ResponseEntity<UserResponseDto>(userResponseDto,
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserResponseDto> updateUsers(
			@RequestBody UserRequestDto userRequestDto)
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
				throw new ApplicationException("User does not exists", HttpStatus.NOT_FOUND.value());
			}
		}
		List<UserDto> resultUserDtos = StreamSupport
				.stream(users.spliterator(), false).map(UserDto::new)
				.collect(Collectors.toList());

		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setUserDtos(resultUserDtos);
		return new ResponseEntity<UserResponseDto>(userResponseDto,
				HttpStatus.OK);

	}

	@Override
	public ResponseEntity<UserResponseDto> getUsers() {
		List<User> users = (List<User>) userDao.getAllUsers();
		List<UserDto> resultUserDtos = StreamSupport
				.stream(users.spliterator(), false).map(UserDto::new)
				.collect(Collectors.toList());

		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setUserDtos(resultUserDtos);
		return new ResponseEntity<UserResponseDto>(userResponseDto,
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDto> getUser() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof String
				&& ((String) principal).equals("anonymousUser")) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		UserDetails userDetails = (UserDetails) principal;
		User user = userDao.findByUsername(userDetails.getUsername());
		UserDto userDto = new UserDto(user);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteUser(@RequestBody UserRequestDto userRequestDto)
			throws ApplicationException {
		for (UserDto userDto : userRequestDto.getUserDtos()) {
			User user = userDao.findByUsername(userDto.getUsername());
			if (user != null) {
				userDao.deleteUser(user);
			} else {
				throw new ApplicationException("User does not exists", HttpStatus.NOT_FOUND.value());
			}
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
