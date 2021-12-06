package com.lysenko.payments.servlet.admin;


import com.lysenko.payments.model.dao.AccountDao;
import com.lysenko.payments.model.dao.RequestUnblockDao;
import com.lysenko.payments.model.entity.account.Status;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/request_unblock_account")
public class AdminUnblockAccount extends HttpServlet {
    private final Logger log = Logger.getLogger(AdminUnblockAccount.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("try to get accountId from param.");
        int accountId = Integer.parseInt(req.getParameter("id"));
        log.debug("accountId :" + accountId);
        AccountDao accountDao = new AccountDao();
        log.debug("coll toChangeStatusAccount with \"Status.OPEN\" ");
        accountDao.toChangeStatusAccount(Status.OPEN, accountId);
        RequestUnblockDao requestUnblockDao = new RequestUnblockDao();
        log.debug("coll changeRequestStatus");
        requestUnblockDao.changeRequestStatus(accountId);
        resp.sendRedirect("/unblock_account");
    }
}
