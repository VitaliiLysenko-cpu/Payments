package com.lysenko.payments.servlet.admin;

import com.lysenko.payments.model.dao.UserDao;
import com.lysenko.payments.model.entity.user.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private final Logger log = Logger.getLogger(AdminServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("try to get page from param.");
        String pageParam = req.getParameter("page");
        log.debug("page :" + pageParam);
        int page = 1;
        if (pageParam != null) {
            page = Integer.parseInt(pageParam);
        }

        UserDao userDao = new UserDao();
        log.debug("try to get users");
        List<User> users = userDao.getUserDao(page);
        log.debug("users :" + users);
        req.setAttribute("users", users);
        log.debug("coll method getUsersCount");
        final int usersCount = userDao.getUsersCount();
        log.debug("userCount = " + usersCount);
        int numberOfPages = usersCount / UserDao.USERS_PER_PAGE;
        if (usersCount % UserDao.USERS_PER_PAGE != 0) {
            numberOfPages++;
        }
        req.setAttribute("numberOfPages", numberOfPages);
        req.getRequestDispatcher("/admin.jsp").forward(req, resp);
    }
}
