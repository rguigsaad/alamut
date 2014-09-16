package fr.alamut.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.alamut.model.Game;

@Path("/game")
public class GameService {

	@GET
	@Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Game getGameInJSON() {
        Game game = new Game("id1","test jeu","idIcon", "in progress");
        return game;
    }
	
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createGameInJSON(Game game) {
		String result = "Game saved : " + game;
		return Response.status(Response.Status.CREATED).entity(result).build();
 
	}
}
