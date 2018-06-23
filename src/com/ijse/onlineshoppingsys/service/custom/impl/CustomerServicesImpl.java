package com.ijse.onlineshoppingsys.service.custom.impl;

import com.ijse.onlineshoppingsys.bo.CustomerBO;
import com.ijse.onlineshoppingsys.bo.CustomerLoginBO;
import com.ijse.onlineshoppingsys.bo.ItemBO;
import com.ijse.onlineshoppingsys.bo.ItemCategoryBO;
import com.ijse.onlineshoppingsys.dao.DAOFactory;
import com.ijse.onlineshoppingsys.dao.custom.CustomerDAO;
import com.ijse.onlineshoppingsys.dao.custom.CustomerLoginDAO;
import com.ijse.onlineshoppingsys.dao.custom.ItemCategoryDAO;
import com.ijse.onlineshoppingsys.dao.custom.ItemDAO;
import com.ijse.onlineshoppingsys.dto.*;
import com.ijse.onlineshoppingsys.resource.ResourceFactory;
import com.ijse.onlineshoppingsys.service.custom.CustomerServices;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerServicesImpl implements CustomerServices {
    CustomerDAO customerDAO;
    CustomerLoginDAO customerLoginDAO;
    Connection connection;
    ItemCategoryDAO itemCategoryDAO;
    ItemDAO itemDAO;
    Map<Integer, String> categories;

    private void initCustDAOs() throws SQLException, ClassNotFoundException {
        if (customerDAO == null || customerLoginDAO == null) {
            try {
                customerDAO = DAOFactory.getDAO(DAOFactory.DAOType.CUSTOMER);
                customerLoginDAO = DAOFactory.getDAO(DAOFactory.DAOType.CUSTOMER_LOGIN);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

    private void initItmDAOs() throws SQLException, ClassNotFoundException {
        try {
            if (itemCategoryDAO == null || itemDAO == null) {
                connection = ResourceFactory.getConnectionResource(ResourceFactory.ResourceConnectionType.MYSQL).getConnection();
                itemCategoryDAO = DAOFactory.getDAO(DAOFactory.DAOType.ITEM_CATEGORY);
                itemDAO = DAOFactory.getDAO(DAOFactory.DAOType.ITEM);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void initItemDAO() throws SQLException, ClassNotFoundException {
        try {
            if (itemCategoryDAO == null || itemDAO == null) {
                connection = ResourceFactory.getConnectionResource(ResourceFactory.ResourceConnectionType.MYSQL).getConnection();
                itemDAO = DAOFactory.getDAO(DAOFactory.DAOType.ITEM);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public CustomerDTO validateLogin(LoginCredentialsDTO login) throws SQLException, ClassNotFoundException {
        initCustDAOs();
        CustomerLoginBO loginBO = new CustomerLoginBO(login.getUsername(), login.getPassword(), 0);
        try {
            int cust_id = customerLoginDAO.checkCredentials(loginBO);
            CustomerBO custBO = customerDAO.fetch(cust_id);
            return new CustomerDTO(
                    custBO.getCust_id(),
                    custBO.getName(),
                    custBO.getNic(),
                    custBO.getMobile()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<ItemCategoryDTO> getCategories() throws SQLException, ClassNotFoundException {
        initItmDAOs();
        List<ItemCategoryDTO> dtos = new ArrayList<>();
        List<ItemCategoryBO> bos = itemCategoryDAO.readAll();
        categories = new HashMap<>();
        for (ItemCategoryBO bo : bos) {
            dtos.add(new ItemCategoryDTO(bo.getCat_id(), bo.getCategory()));
            categories.put(bo.getCat_id(), bo.getCategory());
        }
        return dtos;
    }

    @Override
    public List<ItemDTO> getItems() throws SQLException, ClassNotFoundException {
        initItmDAOs();
        getCategories();
        List<ItemDTO> dtos = new ArrayList<>();
        List<ItemBO> bos = itemDAO.readAll();
        for (ItemBO bo : bos) {
            dtos.add(new ItemDTO(bo.getId(), bo.getName(), bo.getQty(), bo.getUnitPrice(), bo.getCat_id(), categories.get(bo.getCat_id())));
        }
        return dtos;
    }

    @Override
    public List<ItemDTO> getItemOfCategory(int catId) {
        return null;
    }

    @Override
    public boolean addToCart(HttpSession session, CartItemDTO item) throws SQLException, ClassNotFoundException {
        ItemBO itemBO;
        try {
            initItemDAO();
            itemBO = itemDAO.fetch(item.getId());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
        if (itemBO.getQty() >= item.getQty()) {
            item.setName(itemBO.getName());
            item.setUnitPrice(itemBO.getUnitPrice());
            item.setTotalAmount(BigDecimal.valueOf(itemBO.getUnitPrice()).multiply(BigDecimal.valueOf(item.getQty())).doubleValue());
            ReceiptDTO receipt = (ReceiptDTO) session.getAttribute("receipt");
            if (receipt == null) {
                receipt = new ReceiptDTO();
            }
            CartItemDTO existing = receipt.getItemMap().get(item.getId());
            if (existing == null) {
                existing = item;
            } else {
                existing.setQty(existing.getQty() + item.getQty());
                existing.setTotalAmount(existing.getTotalAmount() + item.getTotalAmount());
            }
            receipt.getItemMap().put(item.getId(), existing);
            receipt.setTotalAmount(receipt.getTotalAmount() + existing.getTotalAmount());
            session.setAttribute("receipt", receipt);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeCartItem(HttpSession session, int itemId) {
        ReceiptDTO receipt = (ReceiptDTO) session.getAttribute("receipt");
        if (receipt != null) {
            CartItemDTO existing = receipt.getItemMap().get(itemId);
            if (existing != null) {
                receipt.setTotalAmount(receipt.getTotalAmount() + existing.getTotalAmount());
                session.setAttribute("receipt", receipt);
                receipt.getItemMap().put(itemId, null);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean clearCart(HttpSession session) {
        ReceiptDTO receipt = (ReceiptDTO) session.getAttribute("receipt");
        if (receipt != null) {
            session.setAttribute("receipt", null);
        }
        return false;
    }

    @Override
    public ReceiptDTO purchaseCart(HttpSession session) {
        return null;
    }

    @Override
    public ReceiptDTO purchaseSelectedItems() {
        return null;
    }
}
