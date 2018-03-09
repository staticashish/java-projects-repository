package com.skeleton.springboot.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.skeleton.springboot.dto.CategoryRequestDto;
import com.skeleton.springboot.exception.ApplicationException;

@Controller
@Path("category")
public interface CategoryApi {

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCategory(
			@RequestBody CategoryRequestDto categoryRequestDto ) throws ApplicationException;

	@GET
	@Path("/getcategories")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response getCategories() throws ApplicationException;  
	
}
