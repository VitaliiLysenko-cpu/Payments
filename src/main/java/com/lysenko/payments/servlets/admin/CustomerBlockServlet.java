package com.lysenko.payments.servlets.admin;

import com.lysenko.payments.model.dao.UserDao;
import com.lysenko.payments.model.entity.user.UserStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/customer/*"})
public class CustomerBlockServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String customerId = req.getParameter("userId");
        UserDao userDao = new UserDao();
        final String action = req.getPathInfo().substring(1);
        if (action.equals("block")) {
            userDao.toBlockUser(UserStatus.BLOCKED, customerId);
        } else {
            userDao.toBlockUser(UserStatus.UNBLOCKED,customerId);
        }
        resp.sendRedirect("/admin");
    }
}
