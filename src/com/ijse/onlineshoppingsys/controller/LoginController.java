package com.ijse.onlineshoppingsys.controller;

import com.ijse.onlineshoppingsys.dto.LoginCredentialsDTO;
import com.ijse.onlineshoppingsys.service.ServiceFactory;
import com.ijse.onlineshoppingsys.service.custom.AdminServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginController", urlPatterns = "/loginController")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String access = request.getParameter("access");
        LoginCredentialsDTO login = new LoginCredentialsDTO(request.getParameter("username"), request.getParameter("password"));
        HttpSession session = request.getSession(false);
        System.out.println(session == null);
        if (access.equals("admin")) {
            AdminServices admin = ServiceFactory.getService(ServiceFactory.ServiceType.ADMIN);
            try {
                if (admin.validateLogin(login)) {
                    session = request.getSession();
                    session.setAttribute("access", "admin");
                    response.sendRedirect("http://localhost:8080/home.jsp");
                    System.out.println("Login Controller");
                }
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println("Controller 2");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
