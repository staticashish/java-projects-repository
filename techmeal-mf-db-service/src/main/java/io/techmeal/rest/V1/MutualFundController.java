package io.techmeal.rest.V1;

import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.techmeal.rest.V1.dto.MutualFundDto;
import io.techmeal.rest.V1.dto.UserDto;
import io.techmeal.service.MutualFundService;
import io.techmeal.service.UserService;

@RestController
@RequestMapping("/mutual-fund")
public class MutualFundController {

	@Autowired
	private MutualFundService mfService;

	@Autowired
	private UserService userService;
	
	@GetMapping("/{username}")
	public ResponseEntity<Set<MutualFundDto>> getMutualFundByUser(@RequestBody @PathVariable("username") String username) {
		UserDto userDto = userService.getUser(username);
		if(userDto == null) {
			throw new EntityNotFoundException("User does not exist with username: [ "+username+" ]");
		}
		return new ResponseEntity<Set<MutualFundDto>>(mfService.getMutualFundsByUser(userDto), HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<MutualFundDto> createMutualFund(@RequestBody MutualFundDto mutualFundDto) {
		return new ResponseEntity<MutualFundDto>(mfService.createMutualFund(mutualFundDto), HttpStatus.OK);
	}
	
	@PutMapping("/delete")
	public ResponseEntity<Long> deleteMutualFund(@RequestBody MutualFundDto mutualFundDto) {
		
		if(mfService.getMutualFundsById(mutualFundDto.getId()) == null) {
			throw new EntityNotFoundException("Mutual Fund does not exist with id: [ "+mutualFundDto.getId()+" ]");
		}
		return new ResponseEntity<Long>(mfService.delete(mutualFundDto), HttpStatus.OK);
	}

}
