package com.skeleton.springboot;

import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Element;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.skeleton.springboot.api.UserApi;
import com.skeleton.springboot.dto.UserDto;
import com.skeleton.springboot.dto.UserRequestDto;
import com.skeleton.springboot.dto.UserResponseDto;
import com.skeleton.springboot.exception.ApplicationException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestUserController {

	@Autowired
	UserApi userApi;

	@Test
	public void shouldCreateUser() throws ApplicationException {

		UserDto userDto = new UserDto();
		userDto.setUsername("falesentry");
		userDto.setPassword("falesentry");

		UserRequestDto requestDto = new UserRequestDto();
		List<UserDto> userDtos = new ArrayList<UserDto>();

		userDtos.add(userDto);
		requestDto.setUserDtos(userDtos);

		ResponseEntity<UserResponseDto> responseEntity = userApi.createUsers(requestDto);
	}

/*	@Test
	public void getJson() throws JsonParseException, JsonMappingException, JsonGenerationException, IOException{
		Role role = new Role();
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		UserDto userDto =  new UserDto();
		userDto.setRoles(roles);
		List<UserDto> userDtos = new ArrayList<UserDto>();
		userDtos.add(userDto);
		UserRequestDto dto = new UserRequestDto();
		dto.setUserDtos(userDtos);
		Gson gson = new Gson();
		String json = gson.toJson(dto);
		System.out.println("json : "+json);

		ObjectMapper objectMapper = new ObjectMapper();
		Object jsonJackson = objectMapper.readValue(
				objectMapper.writeValueAsString(dto), Object.class);

		System.out.println("jsonJackson: "+objectMapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(jsonJackson));
	}
*/
	@Test
	public void testCaching() throws Exception{
		net.sf.ehcache.CacheManager cacheManager = net.sf.ehcache.CacheManager.getInstance();
		net.sf.ehcache.Ehcache cache1 = (net.sf.ehcache.Ehcache) cacheManager.getCache("users");

		for (Object key: cache1.getKeys()) {
			Element mapValue5 = cache1.get(key);
			System.out.println("mapValue5: "+mapValue5);
		}
	}	

}
