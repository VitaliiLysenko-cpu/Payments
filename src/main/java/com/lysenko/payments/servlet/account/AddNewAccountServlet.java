package com.lysenko.payments.servlet.account;

import com.lysenko.payments.model.dao.AccountDao;
import com.lysenko.payments.model.entity.user.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/add_account")
public class AddNewAccountServlet extends HttpServlet {
    private final Logger log = Logger.getLogger(AddNewAccountServlet.class);
    private final AccountDao accountDao = new AccountDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("try to get session");
        HttpSession session = req.getSession();
        log.debug("try to get user");
        User user = (User) session.getAttribute("user");
        log.debug("try to create account");
        accountDao.createAccount(user.getUserId());
        resp.sendRedirect("/user");
    }
}
