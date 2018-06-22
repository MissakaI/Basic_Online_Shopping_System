package com.ijse.onlineshoppingsys.controller;

import com.ijse.onlineshoppingsys.dto.CustomerDTO;
import com.ijse.onlineshoppingsys.dto.LoginCredentialsDTO;
import com.ijse.onlineshoppingsys.service.ServiceFactory;
import com.ijse.onlineshoppingsys.service.custom.AdminServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ManageCustomerController", urlPatterns = "/manageCustomerController")
public class ManageCustomerController extends HttpServlet {
    AdminServices adminService;

    public ManageCustomerController() {

    }

    private void initServices() {
        adminService = ServiceFactory.getService(ServiceFactory.ServiceType.ADMIN);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (adminService == null) {
            initServices();
        }

        String action = (String) req.getParameter("action");
        if (action.equals("delete")) {
            String cust_id = (String) req.getParameter("cust_id");
            try {
                adminService.removeCustomer(Integer.parseInt(cust_id));
            } catch (SQLException | ClassNotFoundException e) {
                throw new ServletException(e.getMessage(), e.getCause());
            }
            resp.sendRedirect(req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/manageCustomers.jsp");
        } else if (action.equals("edit")) {
            CustomerDTO dto = new CustomerDTO(
                    Integer.parseInt(req.getParameter("cust_id")),
                    req.getParameter("cust_name"),
                    Integer.parseInt(req.getParameter("nic")),
                    Integer.parseInt(req.getParameter("mobile"))
            );
            try {
                adminService.updateCustomer(dto);
            } catch (SQLException | ClassNotFoundException e) {
                throw new ServletException(e.getMessage(), e.getCause());
            }
            resp.sendRedirect(req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/manageCustomers.jsp");
        } else if (action.equals("new")) {
            CustomerDTO dto = new CustomerDTO(
                    0,
                    req.getParameter("cust_name"),
                    Integer.parseInt(req.getParameter("nic")),
                    Integer.parseInt(req.getParameter("mobile"))
            );
            LoginCredentialsDTO login = new LoginCredentialsDTO(
                    req.getParameter("username"),
                    req.getParameter("password")
            );
            dto.setLogin(login);
            try {
                adminService.newCustomer(dto);
            } catch (SQLException | ClassNotFoundException e) {
                throw new ServletException(e.getMessage(), e.getCause());
            }
            resp.sendRedirect(req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/manageCustomers.jsp");
        }
    }

    public List<CustomerDTO> viewCustomers() throws ServletException {
        if (adminService == null) {
            initServices();
        }
        try {
            return adminService.viewCustomers();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException(e.getMessage(), e.getCause());
        }
    }
}
