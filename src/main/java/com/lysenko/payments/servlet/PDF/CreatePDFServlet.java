package com.lysenko.payments.servlet.PDF;

import com.lysenko.payments.CreatePDF;
import com.lysenko.payments.model.dao.PaymentDao;
import com.lysenko.payments.model.entity.payment.Payment;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/create_pdf")
public class CreatePDFServlet extends HttpServlet {
    private final Logger log = Logger.getLogger(CreatePDFServlet.class);
    private final PaymentDao paymentDao = new PaymentDao();
 CreatePDF createPDF = new CreatePDF();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Payment result = paymentDao.searchDataForPDF(id);
        createPDF.createPaymentPDF(result);
        resp.sendRedirect("/user");
    }
}
