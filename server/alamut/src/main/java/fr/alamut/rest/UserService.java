package fr.alamut.rest;

import fr.alamut.model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
public class UserService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserInJSON() {
        User user = new User("zf","zf","fzf");
        return user;
    }

}
