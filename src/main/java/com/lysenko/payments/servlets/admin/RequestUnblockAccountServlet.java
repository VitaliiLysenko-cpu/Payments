package com.lysenko.payments.servlets.admin;

import com.lysenko.payments.model.dao.RequestUnblockDao;
import com.lysenko.payments.model.entity.request.RequestUnblock;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/unblock_account")
public class RequestUnblockAccountServlet extends HttpServlet {
    private final Logger log = Logger.getLogger(RequestUnblockAccountServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestUnblockDao requests = new RequestUnblockDao();
        log.debug("try to get accounts to unblock");
        List<RequestUnblock> requestUnblocks = requests.getAccountsToUnblock();
        log.debug("try to set attribute \"requests\"");
        req.setAttribute("requests", requestUnblocks);
        log.debug("forward to \"unblock_account.jsp\"");
        req.getRequestDispatcher("/unblock_account.jsp").forward(req, resp);
    }
}
