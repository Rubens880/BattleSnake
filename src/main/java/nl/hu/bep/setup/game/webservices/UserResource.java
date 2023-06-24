package nl.hu.bep.setup.game.webservices;

import nl.hu.bep.setup.security.MyUser;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;



@Path("user")
public class UserResource {

    //controleert of jwt valid is zo ja dan geeft die de user terug
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@Context SecurityContext securityContext) {
        try {
            MyUser myUser = (MyUser) securityContext.getUserPrincipal();
            MyUser user = MyUser.getUserByUsername(myUser.getName());

            if (user == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.ok(user).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("JWT token is invalid.")
                    .build();
        }
    }
}
