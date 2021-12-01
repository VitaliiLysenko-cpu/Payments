package com.lysenko.payments.utils;

import org.junit.jupiter.api.Test;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

class EncodingFilterTest {
    EncodingFilter encodingFilter = new EncodingFilter();
    @Test
    public void doFilterTest_encodingNull() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        when(request.getCharacterEncoding()).thenReturn(null);

        encodingFilter.doFilter(request, response, filterChain);

        verify(request).getCharacterEncoding();
        verify(request).setCharacterEncoding(anyString());
        verify(filterChain).doFilter(request,response);
        verifyNoMoreInteractions(request,response,filterChain);
    }
    @Test
    public void doFilterTest_encodingNotNull() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        when(request.getCharacterEncoding()).thenReturn("UTF-8");

        encodingFilter.doFilter(request, response, filterChain);

        verify(request).getCharacterEncoding();
        verify(filterChain).doFilter(request,response);
        verifyNoMoreInteractions(request,response,filterChain);
    }
    @Test
    public void initTest(){
        FilterConfig config = mock(FilterConfig.class);
        encodingFilter.init(config);
        verify(config).getInitParameter("encoding");
        verifyNoMoreInteractions(config);
    }
}