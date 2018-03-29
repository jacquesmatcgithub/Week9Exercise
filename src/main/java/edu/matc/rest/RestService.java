package edu.matc.rest;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * The RestService class
 */
public class RestService {
    private final Logger logger = LogManager.getLogger(this.getClass());

    GenericDao userDao = new GenericDao(User.class);

    /**
     * The getAllUsers method will return all the users in the user table
     * @return  Json String
     */
    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users/")
    public Response getAllUsers() {
        List<User> users;
        users = userDao.getAll();

        String jsonString = parseIntoJson(users);

        if (jsonString.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Not found").build();
        } else {
            return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();

        }
    }


    /**
     * The getSpecificUser method will return a specific user
     * @param   param
     * @return  Json String
     */
    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users/{param}")
    public Response getSpecificUser(@PathParam("param") String param) {
        List<User> users;
        users = userDao.getByPropertyLike("lastName", param);

        String jsonString = parseIntoJson(users);

        if (jsonString.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Not found").build();
        } else {
            return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
        }
    }


    /**
     * The parseIntoJson method takes the List of User objects and parses them into a json string
     * @param users
     * @return
     */
    private String parseIntoJson(List<User> users) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        int jsonObjCount = 0;

        if (users.size() == 0) {
            return "";
        }

        try {
            for (User thisUser : users) {
                jsonObjCount += 1;
                jsonString += mapper.writeValueAsString(thisUser);

                if (jsonObjCount < users.size()) {
                    jsonString += ",";
                }
            }
            if (users.size() > 1) {
                jsonString = "[" + jsonString + "]";
            }
        } catch (JsonGenerationException e) {
            logger.error(e);
        } catch (JsonMappingException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }

        return jsonString;

    }

}
