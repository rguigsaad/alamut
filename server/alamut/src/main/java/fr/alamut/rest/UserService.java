package fr.alamut.rest;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;

import fr.alamut.controler.UserControler;
import fr.alamut.model.User;

@Path("/user")
public class UserService {
	private final UserControler userControler;
	private  Logger logger = Logger.getLogger(UserService.class.getName() );
	@Inject
	public UserService(UserControler userControler) {
		this.userControler = userControler;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> get() {
		List<User> listUser = null;
		try {
			listUser = userControler.getAllUsers();
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.toString());
			Response response = Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage()).build();
			throw new WebApplicationException(response);
		}
		return listUser;
	}

	@GET
	@Path("/{emailAdress}")
	@Produces(MediaType.APPLICATION_JSON)
	public User get(@PathParam("emailAdress") String emailAdress) {
		User user;
		try {
			user = userControler.getUserByEmailAdress(emailAdress);
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.toString());
			Response response = Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage()).build();
			throw new WebApplicationException(response);
		}
		return user;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response post(User user) {
		try {
			userControler.createUser(user);
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.toString());
			Response response = Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage()).build();
			throw new WebApplicationException(response);
		}
		return Response.status(201).entity(user.toString()).build();

	}
}