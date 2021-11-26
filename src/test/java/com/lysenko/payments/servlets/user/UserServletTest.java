package com.lysenko.payments.servlets.user;

import com.lysenko.payments.model.dao.AccountDao;
import com.lysenko.payments.model.entity.account.Account;
import com.lysenko.payments.model.entity.user.User;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServletTest {

    private final UserServlet userServlet = new UserServlet();

    @Test
    void doGetTest() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        User user = mock(User.class);
        AccountDao dao = mock(AccountDao.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        String page = "2";
        int intPage = 2;
        String sortBy = "sortBy";
        int userId = 1;
        List<Account> accountList = new ArrayList<>();
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("page")).thenReturn(page);
        when(request.getParameter("sortBy")).thenReturn(sortBy);
        when(user.getUserId()).thenReturn(userId);
        when(dao.getAllUserAccounts(userId, intPage, sortBy)).thenReturn(accountList);
        when(dao.getAccountsCount(userId)).thenReturn(10);
        when(request.getRequestDispatcher("/user.jsp")).thenReturn(dispatcher);
        userServlet.setAccountDao(dao);

        userServlet.doGet(request, response);

        verify(request).getSession();
        verify(session).getAttribute("user");
        verify(request).getParameter("page");
        verify(request).getParameter("sortBy");
        verify(dao).getAllUserAccounts(userId, intPage, sortBy);
        verify(user, times(2)).getUserId();
        verify(request).setAttribute("accounts", accountList);
        verify(dao).getAccountsCount(userId);
        verify(request).setAttribute("numberOfPages", 4);
        verify(request).getRequestDispatcher("/user.jsp");
        verify(dispatcher).forward(request, response);
        verifyNoMoreInteractions(request, response, session, user, dao, dispatcher);
    }
}