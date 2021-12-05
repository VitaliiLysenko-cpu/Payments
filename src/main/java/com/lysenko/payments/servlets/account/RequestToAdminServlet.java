package com.lysenko.payments.servlets.account;

import com.lysenko.payments.model.dao.AccountDao;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/sent-request")
public class RequestToAdminServlet extends HttpServlet {
    private final Logger log = Logger.getLogger(RequestToAdminServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("try to get accountId from param.");
        int accountId = Integer.parseInt(req.getParameter("id"));
        int page = Integer.parseInt(req.getParameter("page"));
        log.debug(" accountId :" + accountId);
        AccountDao accountDao = new AccountDao();
        if (accountDao.toCheckRequestWithAccountId(accountId)) {
            log.debug("coll toSentRequest with accountId");
            resp.sendRedirect("/user?info=infoSentRequest&page=" + page);
        } else {
            resp.sendRedirect("/user?info=infoYourRequestCreatedBefore&page=" + page);
        }
    }
}
