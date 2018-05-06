package io.techmeal.rest.V1;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.techmeal.rest.V1.dto.RoleDto;
import io.techmeal.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/all")
	public ResponseEntity<Set<RoleDto>> getAllRoles() {
		Set<RoleDto> roles = roleService.getAllRoles();
		
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<RoleDto> create(@RequestBody RoleDto roleDto) {
		RoleDto newRoleDto = roleService.createRole(roleDto);
		
		return new ResponseEntity<>(newRoleDto, HttpStatus.OK);
	}
	
}
