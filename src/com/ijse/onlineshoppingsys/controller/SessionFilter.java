package com.ijse.onlineshoppingsys.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "SessionFilter", urlPatterns = {"/*", ""})
public class SessionFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("Filter");
        if (req instanceof HttpServletRequest && !(((HttpServletRequest) req).getRequestURI().contains("/build/") ||
                ((HttpServletRequest) req).getRequestURI().contains("/vendors/"))) {

            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;
            System.out.println(request.getRequestURL());
//            System.out.println(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/");
            HttpSession session = request.getSession(false);
            System.out.println(session);
            if (session == null) {
                String access = request.getParameter("access");
                if (access == null) {
                    if (!request.getRequestURI().matches("/|/index.jsp")) {
                        response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/");
                    } else {
                        chain.doFilter(req, resp);
                        request.getSession().invalidate();
                    }
                }else{
                    chain.doFilter(req,resp);
                }
            } else {
                System.out.println("Session 2");
                String access = (String) session.getAttribute("access");
                if (access.equals("admin")) {
                    if (request.getRequestURI().matches("/|/index.jsp")) {
                        response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/manageItems.jsp");
                    } else {
                        chain.doFilter(req, resp);
                    }
                }
                if (access.equals("customer")) {
                    if (request.getRequestURI().matches("/|/index.jsp")) {
                        response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/home.jsp");
                    } else {
                        chain.doFilter(req, resp);
                    }
                }
            }
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) {

    }

}
