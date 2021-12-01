package com.lysenko.payments.utils;

import org.junit.jupiter.api.Test;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.lysenko.payments.utils.LocalizationFilter.LANG_PARAM;
import static org.mockito.Mockito.*;

class LocalizationFilterTest {
    LocalizationFilter filter = new LocalizationFilter();

    @Test
    void doFilterTest() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        FilterChain chain = mock(FilterChain.class);
        when(request.getParameter(LANG_PARAM)).thenReturn("RES");
        when(request.getSession()).thenReturn(session);

        filter.doFilter(request, response, chain);

        verify(request, times(2)).getParameter(LANG_PARAM);
        verify(request).getSession();
        verify(session).setAttribute(LANG_PARAM, "RES");
        verify(chain).doFilter(request, response);
        verifyNoMoreInteractions(request, response, session, chain);
    }
}