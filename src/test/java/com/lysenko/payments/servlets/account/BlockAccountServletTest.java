package com.lysenko.payments.servlets.account;

import com.lysenko.payments.model.dao.AccountDao;
import com.lysenko.payments.model.entity.account.Status;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

class BlockAccountServletTest {
    BlockAccountServlet blockAccountServlet = new BlockAccountServlet();

    @Test
    public void doGetTest() throws IOException {
        AccountDao accountDao = mock(AccountDao.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("id")).thenReturn("1");
        blockAccountServlet.setAccountDao(accountDao);

        blockAccountServlet.doGet(request,response);

        verify(request).getParameter("id");
        verify(accountDao).toChangeStatusAccount(Status.BLOCKED, 1);
        verify(response).sendRedirect("/user");
        verifyNoMoreInteractions(request, response);
    }
}