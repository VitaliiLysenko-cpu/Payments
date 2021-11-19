package com.lysenko.payments.servlets.account;

import com.lysenko.payments.model.dao.AccountDao;
import com.lysenko.payments.model.entity.account.MarkChangeBalance;

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
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Integer accountId = Integer.valueOf(req.getParameter("accountId"));
        String tot = req.getParameter("total");
        if (!tot.isEmpty()) {
            double total = Double.parseDouble(tot);
            AccountDao accountDao = new AccountDao();
            accountDao.changeBalance(total, accountId, MarkChangeBalance.PLUS);
            resp.sendRedirect("/account?id=" + accountId);
        } else {
            resp.sendRedirect(req.getHeader("referer"));
        }
    }
}
