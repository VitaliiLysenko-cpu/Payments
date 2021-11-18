package com.lysenko.payments.servlets.payments;

import com.lysenko.payments.model.account.Account;
import com.lysenko.payments.model.account.AccountDao;
import com.lysenko.payments.model.user.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class NewPaymentCommand implements Command{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        AccountDao accountDao = new AccountDao();
        List<Account> accounts = accountDao.getUserAccounts(user.getUserId());
        req.setAttribute("accounts", accounts);
        req.getRequestDispatcher("/new.jsp").forward(req,resp);
    }
}
