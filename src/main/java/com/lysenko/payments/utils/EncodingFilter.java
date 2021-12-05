package com.lysenko.payments.utils;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private final Logger log = Logger.getLogger(EncodingFilter.class);
    private String encoding;

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        log.debug("");
        String requestEncoding = req.getCharacterEncoding();
        if (requestEncoding == null) {
            req.setCharacterEncoding(encoding);
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) {
        encoding = config.getInitParameter("encoding");
    }
}