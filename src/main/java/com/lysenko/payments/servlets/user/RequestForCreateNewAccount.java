package com.lysenko.payments.servlets.user;

import com.lysenko.payments.model.dao.AccountDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/request/new")
public class RequestForCreateNewAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        AccountDao accountDao = new AccountDao();
        accountDao.requestForCreateNewAccount(id);
        resp.sendRedirect("/user");
    }
}
