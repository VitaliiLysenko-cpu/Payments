package com.lysenko.payments.servlets.authorization;

import com.lysenko.payments.model.dao.UserDao;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

class CreateNewUserServletTest {

    private final CreateNewUserServlet classUnderTest = new CreateNewUserServlet();

    @Test
    void doPostTest() throws IOException {
        UserDao userDao = mock(UserDao.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        classUnderTest.setUserDao(userDao);

        String email = "email";
        String firstName = "firstName";
        String lastName = "lastName";
        String phone = "phone";
        String password = "password";
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("firstname")).thenReturn(firstName);
        when(request.getParameter("lastname")).thenReturn(lastName);
        when(request.getParameter("phone")).thenReturn(phone);
        when(request.getParameter("password")).thenReturn(password);

        classUnderTest.doPost(request, response);

        verify(request).getParameter("email");
        verify(request).getParameter("firstname");
        verify(request).getParameter("lastname");
        verify(request).getParameter("phone");
        verify(request).getParameter("password");
        verify(userDao).registration(email, firstName, lastName, phone, password);
        verify(response).sendRedirect("/login");
        verifyNoMoreInteractions(request, response, userDao);
    }
}