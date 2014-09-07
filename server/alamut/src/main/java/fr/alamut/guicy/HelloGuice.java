package fr.alamut.guicy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.alamut.model.User;

@Path("/helloguice")
public class HelloGuice {
 
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public User get() {
	   User myuser = new User("melodie gence","0123234","15 rue de chambery");
       return myuser;
   }
}