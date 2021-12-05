package com.lysenko.payments.servlets.admin;

import com.lysenko.payments.model.dao.UserDao;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/create_user_from_admin")
public class CreateNewUserFromAdminServlet extends HttpServlet {
    private final Logger log = Logger.getLogger(CreateNewUserFromAdminServlet.class);
    private final UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.debug("try to get parameters");
        String email = req.getParameter("email");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String phoneNum = req.getParameter("phone");
        String role = req.getParameter("role");
        String password = req.getParameter("password");
        boolean res;
        if (role.equalsIgnoreCase("admin")) {
            res = userDao.registration(email, firstname, lastname, phoneNum, password, role);
        } else {
            res = userDao.registration(email, firstname, lastname, phoneNum, password);
        }
        if (res) {
            resp.sendRedirect("/registration_from_admin?success=successfulRegistration");
        } else {
            resp.sendRedirect("/registration_from_admin?error=errorRegistration");
        }
    }
}
