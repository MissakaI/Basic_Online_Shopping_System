package com.ijse.onlineshoppingsys.service.custom.impl;

import com.ijse.onlineshoppingsys.bo.*;
import com.ijse.onlineshoppingsys.dao.DAOFactory;
import com.ijse.onlineshoppingsys.dao.custom.*;
import com.ijse.onlineshoppingsys.dto.CustomerDTO;
import com.ijse.onlineshoppingsys.dto.ItemCategoryDTO;
import com.ijse.onlineshoppingsys.dto.ItemDTO;
import com.ijse.onlineshoppingsys.dto.LoginCredentialsDTO;
import com.ijse.onlineshoppingsys.resource.ResourceFactory;
import com.ijse.onlineshoppingsys.service.custom.AdminServices;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdminServicesImpl implements AdminServices {
    CustomerDAO customerDAO;
    AdminLoginDAO adminLoginDAO;
    CustomerLoginDAO customerLoginDAO;
    Connection connection;
    ItemCategoryDAO itemCategoryDAO;
    ItemDAO itemDAO;

    @Override
    public boolean validateLogin(LoginCredentialsDTO dto) throws SQLException, ClassNotFoundException {
        if (adminLoginDAO == null) {
            try {
                adminLoginDAO = DAOFactory.getDAO(DAOFactory.DAOType.ADMIN_LOGIN);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw e;
            }
        }
        AdminLoginBO bo = new AdminLoginBO(dto.getUsername(), dto.getPassword());
        return adminLoginDAO.checkCredentials(bo);
    }

    private void initCustDAOs() throws SQLException, ClassNotFoundException {
        if (customerDAO == null || customerLoginDAO == null) {
            try {
                connection = ResourceFactory.getConnectionResource(ResourceFactory.ResourceConnectionType.MYSQL).getConnection();
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

    private void initCustomerDAO() throws SQLException, ClassNotFoundException {
        if (customerDAO == null) {
            try {
                customerDAO = DAOFactory.getDAO(DAOFactory.DAOType.CUSTOMER);
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
    public boolean newCustomer(CustomerDTO cust) throws SQLException, ClassNotFoundException {
        initCustDAOs();
        customerDAO.setConnection(connection);
        customerLoginDAO.setConnection(connection);
        connection.setAutoCommit(false);
        try {
            CustomerBO custBO = new CustomerBO(
                    cust.getCust_id(),
                    cust.getName(),
                    cust.getNic(),
                    cust.getMobile()
            );
            int cust_id = customerDAO.create(custBO);
            if (cust_id != -1) {
                CustomerLoginBO custLoginBO = new CustomerLoginBO(
                        cust.getLogin().getUsername(),
                        cust.getLogin().getPassword(),
                        cust_id
                );
                if (customerLoginDAO.create(custLoginBO) != -1) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                }
            }
        } catch (SQLException e) {
            connection.setAutoCommit(true);
            throw e;
        }
        return false;
    }

    @Override
    public boolean updateCustomer(CustomerDTO cust) throws SQLException, ClassNotFoundException {
        initCustDAOs();
        customerDAO.setConnection(connection);
        customerLoginDAO.setConnection(connection);
        boolean res = false;
        try {
            CustomerBO custBO = new CustomerBO(
                    cust.getCust_id(),
                    cust.getName(),
                    cust.getNic(),
                    cust.getMobile()
            );
            res = customerDAO.update(custBO);

            CustomerLoginBO custLoginBO = new CustomerLoginBO(
                    cust.getLogin().getUsername(),
                    cust.getLogin().getPassword(),
                    cust.getCust_id()
            );
            res = customerLoginDAO.update(custLoginBO);
            if (res) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
            }
        } catch (SQLException e) {
            connection.setAutoCommit(true);
            throw e;
        }
        return false;
    }

    @Override
    public List<CustomerDTO> viewCustomers() throws SQLException, ClassNotFoundException {
        initCustDAOs();
        List<CustomerBO> boList = customerDAO.readAll();
        List<CustomerDTO> dtoList = boList.stream()
                .map(bo -> new CustomerDTO(bo.getCust_id(), bo.getName(), bo.getNic(), bo.getMobile()))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public boolean removeCustomer(int cust_id) throws SQLException, ClassNotFoundException {
        initCustomerDAO();
        return customerDAO.delete(cust_id);
    }

    @Override
    public CustomerDTO searchCustomer(int cust_id) throws SQLException, ClassNotFoundException {
        initCustomerDAO();
        CustomerBO custBO = customerDAO.fetch(cust_id);
        return new CustomerDTO(custBO.getCust_id(), custBO.getName(), custBO.getNic(), custBO.getMobile());
    }

    @Override
    public boolean newItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        initItemDAO();
        ItemBO bo = new ItemBO(
                0,
                dto.getName(),
                dto.getQty(),
                dto.getUnitPrice(),
                dto.getCat_id()
        );
        return itemDAO.create(bo) > 0;
    }

    @Override
    public boolean newCategory(ItemCategoryDTO dto) throws SQLException, ClassNotFoundException {
        initItmDAOs();
        ItemCategoryBO bo = new ItemCategoryBO(dto.getCat_id(), dto.getCategory());
        return itemCategoryDAO.create(bo) > 0;
    }

    @Override
    public boolean newItemAndCategory(ItemDTO item, ItemCategoryDTO cat) throws SQLException, ClassNotFoundException {
        initItmDAOs();
        itemDAO.setConnection(connection);
        itemCategoryDAO.setConnection(connection);
        connection.setAutoCommit(false);
        try {
            if (newCategory(cat))
                if (newItem(item)) {
                    connection.commit();
                    return true;
                }
            connection.rollback();
        } catch (SQLException | ClassNotFoundException e) {
            connection.setAutoCommit(true);
            e.printStackTrace();
            throw e;
        }

        return false;
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        initItemDAO();
        ItemBO bo = new ItemBO(
                0,
                dto.getName(),
                dto.getQty(),
                dto.getUnitPrice(),
                dto.getCat_id()
        );
        return itemDAO.update(bo);
    }

    @Override
    public boolean removeItem(int id) throws SQLException, ClassNotFoundException {
        initItemDAO();
        return itemDAO.delete(id);
    }

    Map<Integer, String> categories;
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
}
