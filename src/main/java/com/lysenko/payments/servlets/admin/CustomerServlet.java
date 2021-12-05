package com.lysenko.payments.servlets.admin;

import com.lysenko.payments.model.dao.AccountDao;
import com.lysenko.payments.model.entity.account.Account;
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
        String pageParam = req.getParameter("page");
        int page = 1;
        if (pageParam != null) {
            page = Integer.parseInt(pageParam);
        }
        log.debug("customerId : " + customerId);
        AccountDao accountDao = new AccountDao();
        log.debug("try to parse int customerId and get all sorted accounts");
        List<Account> accounts = accountDao.getAllUserAccounts(Integer.parseInt(customerId), page);
        log.debug("try to set attribute \"accounts\"");
        final int accountsCount = accountDao.getAccountsCount(Integer.parseInt(customerId));
        int numberOfPages = accountsCount / AccountDao.ACCOUNT_GET_PAGE;
        if (accountsCount % AccountDao.ACCOUNT_GET_PAGE != 0) {
            numberOfPages++;
        }

        req.setAttribute("numberOfPages", numberOfPages);
        req.setAttribute("accounts", accounts);
        log.debug("try to set attribute \"customerId\"");
        req.setAttribute("customerId", customerId);
        log.debug("forward to \"customer.jsp\"");
        req.setAttribute("page", page);
        req.getRequestDispatcher("/customer.jsp").forward(req, resp);
    }
}
