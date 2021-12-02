package com.lysenko.payments.servlets.account;

import com.lysenko.payments.model.dao.AccountDao;
import com.lysenko.payments.model.entity.account.Status;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/block")
public class BlockAccountServlet extends HttpServlet {
    private final Logger log = Logger.getLogger(BlockAccountServlet.class);
    AccountDao accountDao = new AccountDao();

    public void setAccountDao(AccountDao accountDao){
        this.accountDao = accountDao;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.debug("try to get accountId from param.");
        int accountId = Integer.parseInt(req.getParameter("id"));
        int page = Integer.parseInt(req.getParameter("page"));
        log.debug(" accountId :" + accountId);

        accountDao.toChangeStatusAccount(Status.BLOCKED, accountId);
        log.debug("coll method toChangeStatusAccount with Status>BLOCKED");
        resp.sendRedirect("/user?page="+ page);
    }
}
