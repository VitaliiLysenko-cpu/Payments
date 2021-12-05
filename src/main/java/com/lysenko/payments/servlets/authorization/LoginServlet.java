package com.lysenko.payments.servlets.authorization;

import com.google.common.annotations.VisibleForTesting;
import com.lysenko.payments.model.dao.UserDao;
import com.lysenko.payments.model.entity.user.Role;
import com.lysenko.payments.model.entity.user.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(LoginServlet.class);
    private UserDao userDao = new UserDao();

    @VisibleForTesting
    void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("email");
        String password = req.getParameter("password");
        log.debug("try to login user");
        User user = userDao.logIn(login, password);

        if (user != null) {
            log.debug("user logged in successfully");
            req.getSession().setAttribute("user", user);
            if (user.getRole() == Role.USER) {
                log.debug("user is regular user, redirecting to /user");
                resp.sendRedirect("/user");
            } else {
                log.debug("user is admin, redirecting to /admin");
                resp.sendRedirect("/admin?page=1");
            }
        } else {
            resp.sendRedirect("/?error=errorLogin");
        }
    }
}
