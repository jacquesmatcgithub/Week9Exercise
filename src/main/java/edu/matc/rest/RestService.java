package edu.matc.rest;

import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * The RestService class
 */
@Path("/user")
public class RestService {
    private final Logger logger = LogManager.getLogger(this.getClass());

    GenericDao userDao = new GenericDao(User.class);

    /**
     * The getUsers method will either call the getAll() or the getByPropertyLike() dao method
     * depending on what the caller passes in as a parameter.
     * @param param
     * @return
     */
    @GET
    @Produces("text/plain")
    @Path("/show/{param}")
    public String getUsers(@PathParam("param") String param) {

        List<User> users;

        if (param.equals("*")) {
            users = userDao.getAll();
        } else {
            users = userDao.getByPropertyLike("lastName", param);
        }

        return buildResultString(users);
    }

    /**
     * The emptyParam method returns an informational message if the caller does not
     * pass a parameter into the webservice.
     * @return
     */
    @GET
    @Produces("text/plain")
    @Path("/show/")
    public String emptyParam() {

        return "Error: Empty param\nUse: /user/show/*\nUse: /user/show/{partial or full last name}";
    }


    /**
     * The buildResultString method concatenates the last & first names of all the users
     * passed to it.
     * @param users
     * @return
     */
    private String buildResultString(List<User> users) {

        String returnStr = "Results:\n";

        for (User user : users) {
            returnStr = returnStr + user.getLastName() + ", " + user.getFirstName() + "\n";
        }

        return returnStr;
    }

}
