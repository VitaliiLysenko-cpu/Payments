package com.lysenko.payments.servlets.account;

import com.lysenko.payments.model.dao.AccountDao;
import com.lysenko.payments.model.entity.account.Status;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/block")
public class BlockAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        int accountId = Integer.parseInt(req.getParameter("id"));
        AccountDao accountDao = new AccountDao();
        accountDao.toBlockAccount(Status.BLOCKED,accountId);
        resp.sendRedirect("/user");
    }
}
