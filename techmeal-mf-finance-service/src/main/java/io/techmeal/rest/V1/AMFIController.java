package io.techmeal.rest.V1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.techmeal.rest.V1.dto.DataRequestDto;
import io.techmeal.rest.V1.dto.DataResponseDto;
import io.techmeal.rest.V1.dto.SearchRequestDto;
import io.techmeal.rest.V1.dto.SearchResponseDto;
import io.techmeal.service.AMFIService;

@RestController
@RequestMapping("/amfi")
public class AMFIController {

	@Autowired
	private AMFIService amfiService;
	
	@PostMapping("/search")
	ResponseEntity<SearchResponseDto> searchMutualFund(@RequestBody SearchRequestDto searchRequestDto){
		return new ResponseEntity<SearchResponseDto>(amfiService.searchMutualFund(searchRequestDto),HttpStatus.OK);
	}
	
	@PostMapping("/getDataByScheme")
	ResponseEntity<DataResponseDto> getDataByScheme(@RequestBody DataRequestDto dataRequestDto){
		return new ResponseEntity<DataResponseDto>(amfiService.getDataForMutualFund(dataRequestDto),HttpStatus.OK);
	}
	
	@PreAuthorize("#oauth2.clientHasRole('ROLE_ADMIN')")
	@GetMapping("/")
	public String getHealth(){
		return "hello";
	}
}
