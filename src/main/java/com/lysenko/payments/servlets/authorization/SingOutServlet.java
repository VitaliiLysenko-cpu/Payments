package com.lysenko.payments.servlets.authorization;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/sign_out")
public class SingOutServlet extends HttpServlet {
    private final Logger log = Logger.getLogger(SingOutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("try to get session");
        final HttpSession session = req.getSession(false);

        if (session != null) {
            log.debug("try to get invalidate session");
            req.getSession().invalidate();
        }
        log.debug("redirect to \"/\"");
        resp.sendRedirect("/");
    }
}
