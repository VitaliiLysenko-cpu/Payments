package com.lysenko.payments.servlets.payments;

import com.lysenko.payments.model.dao.AccountDao;
import com.lysenko.payments.model.entity.account.Account;
import com.lysenko.payments.model.entity.user.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class NewPaymentCommand implements Command {
    private final Logger log = Logger.getLogger(NewPaymentCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String error = req.getParameter("error");
        req.setAttribute("error", error);
        log.debug("try to get session and user");
        User user = (User) req.getSession().getAttribute("user");
        AccountDao accountDao = new AccountDao();
        log.debug("try to get accounts by userId");
        List<Account> accounts = accountDao.getUserOpenAccounts(user.getUserId());
        log.debug("set attribute \"accounts\"");
        req.setAttribute("accounts", accounts);
        log.debug("forward \" / new.jsp\"");
        req.getRequestDispatcher("/new.jsp").forward(req, resp);
    }
}
