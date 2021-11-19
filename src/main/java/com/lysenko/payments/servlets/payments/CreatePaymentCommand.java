package com.lysenko.payments.servlets.payments;


import com.lysenko.payments.model.dao.AccountDao;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreatePaymentCommand implements Command{

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int accountId = Integer.parseInt(req.getParameter("accountId"));
        double total = Double.parseDouble(req.getParameter("total"));
        AccountDao accountDao = new AccountDao();
        accountDao.makePayment(total,accountId);
        resp.sendRedirect("/account?id=" + accountId);
    }
}
