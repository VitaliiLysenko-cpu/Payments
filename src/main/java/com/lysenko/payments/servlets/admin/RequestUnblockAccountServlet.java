package com.lysenko.payments.servlets.admin;
import com.lysenko.payments.model.dao.RequestUnblockDao;
import com.lysenko.payments.model.entity.account.request.RequestUnblock;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/unblock_account")
public class RequestUnblockAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
RequestUnblockDao requests = new RequestUnblockDao();
        List<RequestUnblock> requestUnblocks = requests.getAccountsForUnblock();
       req.setAttribute("requests",requestUnblocks);
       req.getRequestDispatcher("/unblock_account.jsp").forward(req, resp);
    }
}
