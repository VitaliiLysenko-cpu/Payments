package com.lysenko.payments.servlets.account;

import com.lysenko.payments.model.dao.AccountDao;
import com.lysenko.payments.model.dao.CardDao;
import com.lysenko.payments.model.dao.PaymentDao;
import com.lysenko.payments.model.entity.Card;
import com.lysenko.payments.model.entity.payment.Payment;
import com.lysenko.payments.servlets.authorization.LoginServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/account"})
public class AccountServlet extends HttpServlet {
    private final Logger log = Logger.getLogger(AccountServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String pageParam = req.getParameter("page");
        String sortBy = req.getParameter("sortBy");
        //TODO check if in the range
        int page = 1;
        if (pageParam != null) {
            page = Integer.parseInt(pageParam);
        }

        CardDao cardDao = new CardDao();
        PaymentDao paymentDao = new PaymentDao();
        AccountDao accountDao = new AccountDao();
        List<Card> cards = cardDao.getGetAccountCard(id);
        req.setAttribute("cards", cards);

        List<Payment> payments = paymentDao.getPaymentForAccount(id, page, sortBy);
        req.setAttribute("payments", payments);
        final int total = paymentDao.getPaymentsCount(id);
        int numberOfPages = total / paymentDao.ACCOUNTS_PER_PAGE;
        if (total % PaymentDao.ACCOUNTS_PER_PAGE != 0) {
            numberOfPages++;
        }
        double balance = accountDao.getAccountBalance(id);
        req.setAttribute("numberOfPages", numberOfPages);
        req.setAttribute("balance", balance);
        req.getRequestDispatcher("/account.jsp").forward(req, resp);
    }
}
