package io.techmeal.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.techmeal.domain.AppUserDetails;
import io.techmeal.rest.V1.dto.UserDto;

@Service("appUserDetailsService")
public class AppUserDetailsService implements UserDetailsService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${techmeal.mf.db.service.url:http://TECHMEAL-MF-DB-SERVICE}")
	private String dbServiceUrl;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		String userRequestUrl = dbServiceUrl + "/user/{username}";
		
		Map<String, String> params = new HashMap<String, String>();
	    params.put("username", username);
		
	    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(userRequestUrl);
		userRequestUrl = builder.buildAndExpand(params).encode().toUriString();
		
		ResponseEntity<UserDto> response = restTemplate.getForEntity(userRequestUrl, UserDto.class);
		UserDto userDto = response.getBody();
		List<UserDto> userDtos = Arrays.asList(userDto);
		
		return userDtos
				.stream()
				.map(AppUserDetails::new)
				.findFirst()
				.get();
	}

}
