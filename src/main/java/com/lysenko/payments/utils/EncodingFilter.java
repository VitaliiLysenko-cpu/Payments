package com.lysenko.payments.utils;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private String encoding;

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {

        String requestEncoding = req.getCharacterEncoding();
        if (requestEncoding == null) {
            req.setCharacterEncoding(encoding);
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) {
        encoding = config.getInitParameter("encoding");
    }
}