package com.lysenko.payments.utils;

import com.lysenko.payments.model.entity.user.Role;
import com.lysenko.payments.model.entity.user.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class SecurityFilter implements Filter {

    private static final Map<Role, List<String>> accessMap = new HashMap<>();
    private List<String> notFiltered = new ArrayList<>();

    @Override
    public void init(FilterConfig config) {

        accessMap.put(Role.ADMIN, asList(config.getInitParameter("admin")));
        accessMap.put(Role.USER, asList(config.getInitParameter("user")));
        notFiltered = asList(config.getInitParameter("not-filtered"));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if (accessAllowed(request)) {
            chain.doFilter(request, response);
        } else {
            String errorMessages = "You do not have permission to access the requested resource";
            request.setAttribute("errorMessage", errorMessages);

            ((HttpServletResponse) response).sendRedirect("/");
        }
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String action = httpRequest.getRequestURI();
        if (action == null) {
            return false;
        }

        if (notFiltered.contains(action)) {
            return true;
        }

        HttpSession session = httpRequest.getSession(false);
        if (session == null) {
            return false;
        }

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return false;
        }

        Role userRole = user.getRole();
        if (userRole == null) {
            return false;
        }
        return accessMap.get(userRole).contains(action);
    }

    private List<String> asList(String param) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(param);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }
}
