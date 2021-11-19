package com.lysenko.payments.servlets.user;

import com.lysenko.payments.model.entity.account.Account;
import com.lysenko.payments.model.dao.AccountDao;
import com.lysenko.payments.model.entity.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/user"})
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        AccountDao accountDao = new AccountDao();
        //TODO add pagination
        List<Account> accounts = accountDao.getAllUserAccounts(user.getUserId());
        req.setAttribute("accounts", accounts);
        req.getRequestDispatcher("/user.jsp").forward(req, resp);
    }
}
