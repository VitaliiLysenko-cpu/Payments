package com.lysenko.payments.servlets.admin;

import com.lysenko.payments.model.dao.UserDao;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/create_user_from_admin")
public class CreateNewUserFromAdminServlet extends HttpServlet {
    private final Logger log = Logger.getLogger(CreateNewUserFromAdminServlet.class);
    private UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("try to get parameters");
        String email = req.getParameter("email");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String phoneNum = req.getParameter("phone");
        String role = req.getParameter("role");
        String password = req.getParameter("password");

        if (role.equalsIgnoreCase("admin")) {
            userDao.registration(email, firstname, lastname, phoneNum, password, role);
        } else {
            userDao.registration(email, firstname, lastname, phoneNum, password);
        }
//TODO message about successful registration

        req.getRequestDispatcher("registration_admin.jsp").forward(req, resp);
    }
}
