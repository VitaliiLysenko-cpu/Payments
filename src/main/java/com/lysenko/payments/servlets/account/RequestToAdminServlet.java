package com.lysenko.payments.servlets.account;

import com.lysenko.payments.model.account.AccountDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/sent-request")
public class RequestToAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int accountId = Integer.parseInt(req.getParameter("id"));
        AccountDao accountDao = new AccountDao();
        accountDao.toSentRequest(accountId);
        resp.sendRedirect("/user");
    }
}
