package com.lysenko.payments.servlets.admin;

import com.lysenko.payments.model.entity.account.Account;
import com.lysenko.payments.model.dao.AccountDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String customerId = req.getParameter("id");
        AccountDao accountDao = new AccountDao();
        //TODO add pagination
        List<Account> accounts = accountDao.getAllUserAccounts(Integer.parseInt(customerId));
        req.setAttribute("accounts", accounts);
        req.setAttribute("customerId", customerId);
        req.getRequestDispatcher("/customer.jsp").forward(req, resp);

    }
}
