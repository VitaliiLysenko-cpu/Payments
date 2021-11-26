package com.lysenko.payments.servlets.admin;

import com.lysenko.payments.model.entity.account.Account;
import com.lysenko.payments.model.dao.AccountDao;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    private final Logger log = Logger.getLogger(CustomerServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("try to get customerId from param.");
        final String customerId = req.getParameter("id");
        log.debug("customerId : " + customerId);
        AccountDao accountDao = new AccountDao();
        log.debug("try parse to int customerId and get all sorted accounts");
        List<Account> accounts = accountDao.getAllSortedUserAccounts(Integer.parseInt(customerId));
        log.debug("try set attribute \"accounts\"");
        req.setAttribute("accounts", accounts);
        log.debug("try set attribute \"customerId\"");
        req.setAttribute("customerId", customerId);
        log.debug("forward to \"customer.jsp\"");
        req.getRequestDispatcher("/customer.jsp").forward(req, resp);

    }
}
