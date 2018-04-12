package io.techmeal.rest.V1;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userinfo")
public class UserController {

	@GetMapping
	public Principal user(Principal principal){
		return principal;
	}
	
}
