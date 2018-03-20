package edu.matc.controller;

import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// import edu.matc.persistence.UserDao;

/**
 * Search by Last Name
 * @author pwaite
 */

@WebServlet(
        urlPatterns = {"/searchLastName"}
)

public class SearchLastName extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String searchTerm = req.getParameter("searchTerm").trim();

        logger.info("searchTerm: " + searchTerm);

        GenericDao userDao = new GenericDao(User.class);

        req.setAttribute("message", "Searching by Last Name");

        req.setAttribute("users", userDao.getByPropertyLike("lastName", searchTerm));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);
    }
}