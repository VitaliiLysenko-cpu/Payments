package com.lysenko.payments.servlets.admin;


import com.lysenko.payments.model.dao.AccountDao;
import com.lysenko.payments.model.entity.account.Status;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/customer-account/*")
public class ChangeAccountStatusServlet extends HttpServlet {
    private final Logger log = Logger.getLogger(ChangeAccountStatusServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("try to get accountId from param.");
        int accountId = Integer.parseInt(req.getParameter("accountId"));
        log.debug("accountId : " + accountId);
        log.debug("try to get parameter \"customerId\"");
        String customerId = req.getParameter("customerId");
        log.debug("customerId : " + customerId);
        AccountDao accountDao = new AccountDao();
        log.debug("get action from path string");
        final String action = req.getPathInfo().substring(1);
        if (action.equals("block")) {
            log.debug("try to change status to BLOCKED with " + action);
            accountDao.toChangeStatusAccount(Status.BLOCKED, accountId);
        } else if (action.equals("unblock")) {
            log.debug("try to change status to OPEN with " + action);
            accountDao.toChangeStatusAccount(Status.OPEN, accountId);
        }
        log.debug("sendRedirect to \"customer?id\" ");
        resp.sendRedirect("/customer?id=" + customerId);
    }
}
