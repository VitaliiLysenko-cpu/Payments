package com.lysenko.payments.servlets.payments;


import com.lysenko.payments.model.dao.AccountDao;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreatePaymentCommand implements Command {
    private final Logger log = Logger.getLogger(CreatePaymentCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.debug("try get parameters accountId and total");
        String accId = req.getParameter("accountId");
        String tot = req.getParameter("total");
        log.debug("accountId: " + accId + ", total: " + tot);
        if (!accId.isEmpty() && !tot.isEmpty()) {
            log.debug("try parse accountId and total");
            int accountId = Integer.parseInt(accId);
            double total = Double.parseDouble(tot);
            log.debug("accountId: " + accountId + ", total: " + total);
            AccountDao accountDao = new AccountDao();
            log.debug("coll \"makePayment\"");
            accountDao.makePayment(total, accountId);
            log.debug("try sent redirect \"\"account?id=\" + accountId\"");
            resp.sendRedirect("/account?id=" + accountId);
        } else {
            log.debug("try sent redirect \"referer\"");
            resp.sendRedirect(req.getHeader("referer"));
        }
    }
}
