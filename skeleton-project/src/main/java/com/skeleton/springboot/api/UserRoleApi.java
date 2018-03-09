package com.skeleton.springboot.api;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.skeleton.springboot.dto.RoleRequestDto;
import com.skeleton.springboot.exception.ApplicationException;

@Controller
@Path("role")
public interface UserRoleApi {

	@GET
	@Path("/getallroles")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRoles();
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("ROLE_ADMIN")
	public Response createRoles(
			@RequestBody RoleRequestDto roleRequestDto ) throws ApplicationException;  
	
}
