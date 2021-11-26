package com.lysenko.payments.servlets.authorization;

import com.lysenko.payments.model.dao.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

class LoginServletTest {

    private final LoginServlet classUnderTest = new LoginServlet();
    @Mock
    private UserDao userDao;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @BeforeEach
    void setup() {
        classUnderTest.setUserDao(userDao);
    }

    @Test
    void doPostTest_userIsNull() throws ServletException, IOException {
//        String email = "email";
//        String password = "password";
//        when(request.getParameter("email")).thenReturn(email);
//        when(request.getParameter("password")).thenReturn(password);
//        when(userDao.logIn(anyString(), anyString())).thenReturn(null);
//
//        classUnderTest.doPost(request, response);



    }

    @Test
    void doPostTest_userAdmin() {

    }

    @Test
    void doPostTest_userUser() {

    }
}