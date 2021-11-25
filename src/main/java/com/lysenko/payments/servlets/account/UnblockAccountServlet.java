package com.lysenko.payments.servlets.account;

import com.lysenko.payments.model.dao.AccountDao;
import com.lysenko.payments.model.entity.account.Status;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/unblock")
public class UnblockAccountServlet extends HttpServlet {
    private final Logger log = Logger.getLogger(UnblockAccountServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("try to get accountId from param.");
        int accountId = Integer.parseInt(req.getParameter("id"));
        log.debug("accountId :" + accountId);
        AccountDao accountDao = new AccountDao();
        log.debug("coll toChangeStatusAccount method with status OPEN");
        accountDao.toChangeStatusAccount(Status.OPEN,accountId);
        resp.sendRedirect("/user");
    }
}
