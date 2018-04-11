package io.techmeal.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient.EurekaServiceInstance;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.discovery.EurekaClient;

import io.techmeal.domain.AppUserDetails;
import io.techmeal.rest.V1.dto.UserDto;

@Service("appUserDetailsService")
public class AppUserDetailsService implements UserDetailsService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${techmeal.mf.db.service.url}")
	private String dbServiceUrl;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private EurekaClient eurekaClient;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//EurekaServiceInstance serviceInstance =  (EurekaServiceInstance) discoveryClient.getInstances(dbServiceUrl).get(0);
		//String dbService = serviceInstance.getInstanceInfo().getHomePageUrl();
		
		
		String ipAddr = eurekaClient.getApplication(dbServiceUrl).getInstances().stream().findFirst().get().getIPAddr();
		int port = eurekaClient.getApplication(dbServiceUrl).getInstances().stream().findFirst().get().getPort();
		//String dbService = discoveryClient.getInstances(dbServiceUrl).stream().findFirst().get().getUri().getPath();
		String dbService = "http://"+ipAddr+":"+port; 
		String userRequestUrl = "https://techmeal-mf-db-service.herokuapp.com/" + "/user/{username}";
		
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
