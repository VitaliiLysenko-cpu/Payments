package com.lysenko.payments.servlets.admin;



import com.lysenko.payments.model.dao.AccountDao;
import com.lysenko.payments.model.dao.RequestUnblockDao;
import com.lysenko.payments.model.entity.account.Status;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/request_unblock_account")
public class AdminUnblockAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      int accountId = Integer.parseInt(req.getParameter("id"));
        AccountDao accountDao = new AccountDao();
        accountDao.toBlockAccount(Status.OPEN, accountId);
        RequestUnblockDao requestUnblockDao = new RequestUnblockDao();
        requestUnblockDao.changeRequestStatus(accountId);
        resp.sendRedirect("/unblock_account");
    }
}
