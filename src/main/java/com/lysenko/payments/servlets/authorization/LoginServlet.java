package com.lysenko.payments.servlets.authorization;

import com.lysenko.payments.model.entity.user.Role;
import com.lysenko.payments.model.entity.user.User;
import com.lysenko.payments.model.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userDao.logIn(login, password);

        if(user != null) {
            req.getSession().setAttribute("user", user);
            if(user.getRole() == Role.USER) {
                resp.sendRedirect("/user");
            }else {
                resp.sendRedirect("/admin?page=1");
            }
        } else {
            req.setAttribute("error", "Invalid credentials");
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }
    }
}
