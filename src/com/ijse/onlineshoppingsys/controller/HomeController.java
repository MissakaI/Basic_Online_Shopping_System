package com.ijse.onlineshoppingsys.controller;

import com.ijse.onlineshoppingsys.dto.CartItemDTO;
import com.ijse.onlineshoppingsys.dto.ItemCategoryDTO;
import com.ijse.onlineshoppingsys.dto.ItemDTO;
import com.ijse.onlineshoppingsys.service.ServiceFactory;
import com.ijse.onlineshoppingsys.service.custom.CustomerServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "HomeController", urlPatterns = "/homeController")
public class HomeController extends HttpServlet {
    CustomerServices customerService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        initServices();
        Integer id = Integer.parseInt(request.getParameter("item_id"));
        Integer qty = Integer.parseInt(request.getParameter("qty"));
        if (id != null && qty != null) {
            CartItemDTO ci = new CartItemDTO(id, "", qty, 0, 0);
            try {
                customerService.addToCart(request.getSession(), ci);
                response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/home.jsp");
            } catch (SQLException | ClassNotFoundException e) {
                throw new ServletException(e.getMessage(), e.getCause());
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    private void initServices() {
        customerService = ServiceFactory.getService(ServiceFactory.ServiceType.CUSTOMER);
    }


    public List<ItemDTO> viewItems() throws ServletException {
        if (customerService == null) {
            initServices();
        }
        try {
            return customerService.getItems();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException(e.getMessage(), e.getCause());
        }
    }

    public List<ItemCategoryDTO> getCategories() throws ServletException {
        if (customerService == null) {
            initServices();
        }
        try {
            return customerService.getCategories();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException(e.getMessage(), e.getCause());
        }
    }
}
