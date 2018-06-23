package com.ijse.onlineshoppingsys.controller;

import com.ijse.onlineshoppingsys.dto.ItemCategoryDTO;
import com.ijse.onlineshoppingsys.dto.ItemDTO;
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

@WebServlet(name = "ManageItemController", urlPatterns = "/manageItemController")
public class ManageItemController extends HttpServlet {
    AdminServices adminService;

    public ManageItemController() {

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
            String item_id = (String) req.getParameter("item_id");
            try {
                adminService.removeItem(Integer.parseInt(item_id));
            } catch (SQLException | ClassNotFoundException e) {
                throw new ServletException(e.getMessage(), e.getCause());
            }
            resp.sendRedirect(req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/manageItems.jsp");
        } else if (action.equals("edit")) {
            ItemDTO dto = new ItemDTO(
                    Integer.parseInt(req.getParameter("item_id")),
                    req.getParameter("item_name"),
                    Integer.parseInt(req.getParameter("qty")),
                    Double.parseDouble(req.getParameter("unit_price")),
                    Integer.parseInt(req.getParameter("cat_id"))
            );
            try {
                adminService.updateItem(dto);
            } catch (SQLException | ClassNotFoundException e) {
                throw new ServletException(e.getMessage(), e.getCause());
            }
            resp.sendRedirect(req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/manageItems.jsp");
        } else if (action.equals("new")) {
            ItemDTO dto = new ItemDTO(
                    Integer.parseInt(req.getParameter("item_id")),
                    req.getParameter("item_name"),
                    Integer.parseInt(req.getParameter("qty")),
                    Double.parseDouble(req.getParameter("unit_price")),
                    Integer.parseInt(req.getParameter("cat_id"))
            );
            try {
                adminService.newItem(dto);
            } catch (SQLException | ClassNotFoundException e) {
                throw new ServletException(e.getMessage(), e.getCause());
            }
            resp.sendRedirect(req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/manageItems.jsp");
        }
    }

    public List<ItemDTO> viewItems() throws ServletException {
        if (adminService == null) {
            initServices();
        }
        try {
            return adminService.getItems();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException(e.getMessage(), e.getCause());
        }
    }

    public List<ItemCategoryDTO> getCategories() throws ServletException {
        if (adminService == null) {
            initServices();
        }
        try {
            return adminService.getCategories();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException(e.getMessage(), e.getCause());
        }
    }
}
