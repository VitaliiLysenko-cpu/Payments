package com.lysenko.payments.servlets.admin;

import com.lysenko.payments.model.dao.AccountDao;
import com.lysenko.payments.model.entity.account.Account;
import com.lysenko.payments.model.entity.user.User;
import com.lysenko.payments.model.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.lysenko.payments.model.dao.AccountDao.getAccountCount;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String pageParam = req.getParameter("page");
        int page = 1;
        if (pageParam != null) {
            page = Integer.parseInt(pageParam);
        }

        UserDao userDao = new UserDao();
        AccountDao accountDao = new AccountDao();
        List<User> users = userDao.getUserDao(page);
        req.setAttribute("users", users);
        final int usersCount = userDao.getUsersCount();

        int numberOfPages = usersCount/UserDao.USERS_PER_PAGE;
        if(usersCount % UserDao.USERS_PER_PAGE != 0){
            numberOfPages++;
        }
        req.setAttribute("numberOfPages",numberOfPages);

        //TODO add pagination
        List<Account> accounts = accountDao.getAllSortedUserAccounts(page);
        req.setAttribute("accounts", accounts);

        req.getRequestDispatcher("/admin.jsp").forward(req, resp);
    }
}
