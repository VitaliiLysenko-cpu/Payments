package com.lysenko.payments.servlets.authorization;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    private final Logger log = Logger.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String error = req.getParameter("error");
        String info = req.getParameter("info");
        req.setAttribute("error", error);
        req.setAttribute("info", info);
        log.debug("forward to \"registration.jsp\"");
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }
}
