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

import fr.alamut.controler.GameControler;
import fr.alamut.model.Game;


@Path("/game")
public class GameService {
	private final GameControler gameControler;
	private  Logger logger = Logger.getLogger(GameService.class.getName() );
	@Inject
	public GameService(GameControler gameControler) {
		this.gameControler = gameControler;
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Game> get() {
		List<Game> listGame = null;
		try {
			listGame = gameControler.getAllGamesALL();
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.toString());
			Response response = Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage()).build();
			throw new WebApplicationException(response);
		}
		return listGame;
	}

	@GET
	@Path("/{idOwnerPhoneNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Game> get(@PathParam("idOwnerPhoneNumber") String idOwnerPhoneNumber) {
		List<Game> listGame = null;
		try {
			listGame = gameControler.getAllGames(idOwnerPhoneNumber);
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.toString());
			Response response = Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage()).build();
			throw new WebApplicationException(response);
		}
		return listGame;
	}

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response post(Game game) {
		try {
			gameControler.createGame(game);
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.toString());
			Response response = Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage()).build();
			throw new WebApplicationException(response);
		}
		return Response.status(201).entity(game.toString()).build();

	}
}