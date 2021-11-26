package com.lysenko.payments.servlets.payments;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/payment/*")
public class PaymentServlet extends HttpServlet {
    private final Logger log = Logger.getLogger(PaymentServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PaymentCommandFactory payComFac = new PaymentCommandFactory(req, resp);
        log.debug("try to found command");
        payComFac.defineCommand().execute(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("coll doPost");
        doPost(req, resp);
    }
}
