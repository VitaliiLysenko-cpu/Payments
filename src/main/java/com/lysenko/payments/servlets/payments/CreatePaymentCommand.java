package com.lysenko.payments.servlets.payments;


import com.lysenko.payments.model.dao.AccountDao;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreatePaymentCommand implements Command{

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String accId = req.getParameter("accountId");
        String tot = req.getParameter("total");
        if (!accId.isEmpty() && !tot.isEmpty()) {
            int accountId = Integer.parseInt(accId);
            double total = Double.parseDouble(tot);
            AccountDao accountDao = new AccountDao();
            accountDao.makePayment(total, accountId);
            resp.sendRedirect("/account?id=" + accountId);
        }else {
            resp.sendRedirect(req.getHeader("referer"));
        }
    }
}
