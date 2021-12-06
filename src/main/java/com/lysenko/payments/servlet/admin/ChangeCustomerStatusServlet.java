package com.lysenko.payments.servlet.admin;

import com.lysenko.payments.model.dao.UserDao;
import com.lysenko.payments.model.entity.user.UserStatus;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/customer/*"})
public class ChangeCustomerStatusServlet extends HttpServlet {
    private final Logger log = Logger.getLogger(ChangeCustomerStatusServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("try to get customerId from param.");
        final String customerId = req.getParameter("userId");
        log.debug("customerId : " + customerId);
        UserDao userDao = new UserDao();
        log.debug("get action from path string");
        final String action = req.getPathInfo().substring(1);
        if (action.equals("block")) {
            log.debug("try to change status to BLOCKED with " + action);
            userDao.toBlockUser(UserStatus.BLOCKED, customerId);
        } else {
            log.debug("try to change status to UNBLOCKED with " + action);
            userDao.toBlockUser(UserStatus.UNBLOCKED, customerId);
        }
        log.debug("sendRedirect to \"req.getHeader(\"referer\")\" ");
        resp.sendRedirect(req.getHeader("referer"));
    }
}
