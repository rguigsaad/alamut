package fr.alamut.rest;

import fr.alamut.model.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/getuser")
public class UserService {
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserInJSON() {
        User user = new User("melodie gence","0123234","15 rue de chambery");
        return user;
    }
    /*
    @POST
	@Path("/postuser")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUserInJSON(User user) {
 
		String result = "User saved : " + user;
		return Response.status(Response.Status.CREATED).entity(result).build();
 
	}
	*/
}
