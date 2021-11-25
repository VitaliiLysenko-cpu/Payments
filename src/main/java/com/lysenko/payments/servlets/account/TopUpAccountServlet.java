package com.lysenko.payments.servlets.account;

import com.lysenko.payments.model.dao.AccountDao;
import com.lysenko.payments.model.entity.account.MarkChangeBalance;
import com.lysenko.payments.servlets.authorization.LoginServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/top_up"})
public class TopUpAccountServlet extends HttpServlet {
    private final Logger log = Logger.getLogger(TopUpAccountServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        log.debug("try to get accountId from param.");
        Integer accountId = Integer.valueOf(req.getParameter("accountId"));
        log.debug("accountId :" + accountId);
        log.debug("try to get total from param");
        String tot = req.getParameter("total");
        log.debug("total :" + tot);
        if (!tot.isEmpty()) {
            double total = Double.parseDouble(tot);
            AccountDao accountDao = new AccountDao();
            accountDao.changeBalance(total, accountId, MarkChangeBalance.PLUS);
            log.debug("total is not empty, change balance");
            resp.sendRedirect("/account?id=" + accountId);
        } else {
            log.debug("total is empty, redirect to getHeader \"referer\"");
            resp.sendRedirect(req.getHeader("referer"));
        }
    }
}
