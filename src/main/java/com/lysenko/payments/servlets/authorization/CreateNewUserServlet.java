package com.lysenko.payments.servlets.authorization;

import com.lysenko.payments.model.dao.UserDao;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/create_user")
public class CreateNewUserServlet extends HttpServlet {
    private final UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        String email = request.getParameter("email");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String phoneNum = request.getParameter("phone");
        String cardNumber = request.getParameter("cardNumber");
        String cvc = request.getParameter("cvc");
        String pin = request.getParameter("pin");
        String expiration = request.getParameter("expiration");
        String password = request.getParameter("password");
        userDao.registration(email,firstname,lastname,phoneNum,password,cardNumber,cvc,pin,expiration);
        response.sendRedirect("/login");
    }
}
