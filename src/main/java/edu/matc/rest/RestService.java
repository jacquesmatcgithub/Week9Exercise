package edu.matc.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/showuser")
public class RestService {
    // Define what type of request this method responds to
    @GET

    // Define what type of content is produced
    @Produces("text/plain")
    @Path("/{userlastname}")
    public Response getMessage(@PathParam("userlastname") String msg) {
        // Return a simple message
        String output = "Hello " + msg;
        return Response.status(200).entity(output).build();
    }


}
