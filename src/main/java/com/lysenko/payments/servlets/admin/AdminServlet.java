package com.lysenko.payments.servlets.admin;

import com.lysenko.payments.model.user.User;
import com.lysenko.payments.model.user.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pageParam = req.getParameter("page");
        int page = 1;
        if (pageParam != null) {
            page = Integer.parseInt(pageParam);
        }

        UserDao userDao = new UserDao();
        List<User> users = userDao.getUserDao(page);
        req.setAttribute("users", users);
        final int total = userDao.getUsersCount();
        int numberOfPages = total/UserDao.USERS_PER_PAGE;
        if(total % UserDao.USERS_PER_PAGE != 0){
            numberOfPages++;
        }
        req.setAttribute("numberOfPages",numberOfPages);

        req.getRequestDispatcher("/admin.jsp").forward(req, resp);
    }
}
