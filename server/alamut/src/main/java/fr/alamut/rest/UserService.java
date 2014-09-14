package fr.alamut.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;

import fr.alamut.controler.UserControler;
import fr.alamut.model.User;

@Path("/user")
public class UserService {
	private final UserControler userControler;
	@Inject
	public UserService(UserControler userControler) {
		this.userControler = userControler;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User get(@QueryParam("emailAdress") String emailAdress) {
		User user = userControler.getUserByEmailAdress(emailAdress);
		return user;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response post(User user) {
		userControler.createUser(user);
		return Response.status(201).entity(user.toString()).build();

	}
}