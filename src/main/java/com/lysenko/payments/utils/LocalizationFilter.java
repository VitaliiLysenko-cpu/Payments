package com.lysenko.payments.utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LocalizationFilter implements Filter {

    static final String LANG_PARAM = "lang";

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getParameter(LANG_PARAM) != null) {
            request.getSession().setAttribute(LANG_PARAM, request.getParameter(LANG_PARAM));
        }
        chain.doFilter(req, resp);
    }
}
