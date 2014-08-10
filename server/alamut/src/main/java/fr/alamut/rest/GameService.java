package fr.alamut.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.alamut.model.Game;

@Path("/getgame")
public class GameService {

	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Game getGameInJSON() {
        Game game = new Game("id1","test jeu","idIcon", "in progress");
        return game;
    }
}
