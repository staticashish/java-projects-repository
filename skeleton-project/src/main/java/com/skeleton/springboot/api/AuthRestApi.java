package com.skeleton.springboot.api;

import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skeleton.springboot.dto.UserRequestDto;
import com.skeleton.springboot.exception.ApplicationException;

@RestController
public interface AuthRestApi {

	 @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	 public Response authenticate(@RequestBody UserRequestDto userRequestDto)
				throws ApplicationException;
}
