package com.lysenko.payments.servlets.account;

import com.lysenko.payments.model.account.AccountDao;
import com.lysenko.payments.model.account.MarkChangeBalance;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/top_up"})
public class TopUpAccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer accountId = Integer.valueOf(req.getParameter("accountId"));
        double total = Double.parseDouble(req.getParameter("total"));
        AccountDao accountDao = new AccountDao();
        accountDao.changeBalance(total,accountId, MarkChangeBalance.PLUS);
        resp.sendRedirect("/account?id=" + accountId);
    }
}
