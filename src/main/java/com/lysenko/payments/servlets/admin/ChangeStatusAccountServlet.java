package com.lysenko.payments.servlets.admin;


import com.lysenko.payments.model.dao.AccountDao;
import com.lysenko.payments.model.entity.account.Status;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/customer-account/*")
public class ChangeStatusAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int accountId = Integer.parseInt(req.getParameter("accountId"));
        String customerId = req.getParameter("customerId");
        AccountDao accountDao = new AccountDao();
        final String action = req.getPathInfo().substring(1);
        if (action.equals("block")) {
            accountDao.toBlockAccount(Status.BLOCKED, accountId);
        }else  if (action.equals("unblock")) {
            accountDao.toBlockAccount(Status.OPEN, accountId);
        }
        resp.sendRedirect("/customer?id=" + customerId);
    }
}
