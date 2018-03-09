package com.skeleton.springboot.api;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.skeleton.springboot.dto.UserRequestDto;
import com.skeleton.springboot.exception.ApplicationException;

@Controller
@Path("user")
public interface UserApi {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	Response getUser();
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
//	@RolesAllowed("ROLE_ADMIN")
	public Response createUsers(
			@RequestBody UserRequestDto userRequestDto ) throws ApplicationException;  
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUsers(
			@RequestBody UserRequestDto userRequestDto ) throws ApplicationException;
	
	@GET
	@Path("/getusers")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers();
	
	
	@POST
	@Path("/authenticate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticate(
			@RequestBody UserRequestDto userRequestDto ) throws ApplicationException;

	
	@PUT
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("ROLE_ADMIN")
	public Response deleteUser(@RequestBody UserRequestDto userRequestDto) throws ApplicationException;

}
