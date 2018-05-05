package io.techmeal.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.techmeal.domain.AppUserDetails;
import io.techmeal.rest.V1.dto.UserDto;

@Service("appUserDetailsService")
public class AppUserDetailsService implements UserDetailsService{

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//EurekaServiceInstance serviceInstance =  (EurekaServiceInstance) discoveryClient.getInstances(dbServiceUrl).get(0);
		//String dbService = serviceInstance.getInstanceInfo().getHomePageUrl();
		
		//String ipAddr = eurekaClient.getApplication(dbServiceUrl).getInstances().stream().findFirst().get().getIPAddr();
		//int port = eurekaClient.getApplication(dbServiceUrl).getInstances().stream().findFirst().get().getPort();
		//String dbService = discoveryClient.getInstances(dbServiceUrl).stream().findFirst().get().getUri().getPath();
		//String dbService = "http://"+ipAddr+":"+port; 
/*		String userRequestUrl = dbServiceUrl + "/user/{username}";
		
		Map<String, String> params = new HashMap<String, String>();
	    params.put("username", username);
		
	    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(userRequestUrl);
		userRequestUrl = builder.buildAndExpand(params).encode().toUriString();
		
		ResponseEntity<UserDto> response = restTemplate.getForEntity(userRequestUrl, UserDto.class);
*/		
		
		UserDto userDto = userService.getUser(username);
		List<UserDto> userDtos = Arrays.asList(userDto);
		return userDtos.stream()
						.map(AppUserDetails::new)
						.findFirst()
						.get();
	}
}
