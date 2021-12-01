package com.lysenko.payments.servlets.authorization;

import com.google.common.annotations.VisibleForTesting;
import com.lysenko.payments.model.dao.UserDao;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/create_user")
public class CreateNewUserServlet extends HttpServlet {
    private final Logger log = Logger.getLogger(CreateNewUserServlet.class);
    private UserDao userDao = new UserDao();

    @VisibleForTesting
    void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug("try to get parameters");
        String email = request.getParameter("email");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String phoneNum = request.getParameter("phone");
        String password = request.getParameter("password");
        log.debug("email: " + email + "firstname: " + firstname + "lastname: " + lastname + "phone: " + phoneNum
                + "password: " + password);
        log.debug("coll \"registration\"");
        Boolean res = userDao.registration(email, firstname, lastname, phoneNum, password);
        if (res == false) {
            response.sendRedirect("/registration?error=errorRegistration");
        } else {
            log.debug("redirect to \"login\"");
            response.sendRedirect("/login");
        }
    }
}
