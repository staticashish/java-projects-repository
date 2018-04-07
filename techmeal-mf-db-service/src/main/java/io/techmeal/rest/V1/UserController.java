package io.techmeal.rest.V1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.techmeal.rest.V1.dto.UserDto;
import io.techmeal.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService; 
	
	@GetMapping("/{username}")
	ResponseEntity<UserDto> getUser(@RequestBody @PathVariable("username") String username){
		UserDto userDto = userService.getUser(username);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	ResponseEntity<UserDto> creatUser(@RequestBody UserDto userDto){
		userDto = userService.createUser(userDto);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	
}
