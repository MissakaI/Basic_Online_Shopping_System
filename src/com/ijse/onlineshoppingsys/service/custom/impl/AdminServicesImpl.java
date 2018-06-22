package com.ijse.onlineshoppingsys.service.custom.impl;

import com.ijse.onlineshoppingsys.bo.AdminLoginBO;
import com.ijse.onlineshoppingsys.bo.CustomerBO;
import com.ijse.onlineshoppingsys.bo.CustomerLoginBO;
import com.ijse.onlineshoppingsys.dao.DAOFactory;
import com.ijse.onlineshoppingsys.dao.custom.*;
import com.ijse.onlineshoppingsys.dto.LoginCredentialsDTO;
import com.ijse.onlineshoppingsys.dto.CustomerDTO;
import com.ijse.onlineshoppingsys.dto.ItemCategoryDTO;
import com.ijse.onlineshoppingsys.dto.ItemDTO;
import com.ijse.onlineshoppingsys.resource.ResourceFactory;
import com.ijse.onlineshoppingsys.service.custom.AdminServices;
import com.ijse.onlineshoppingsys.service.custom.CustomerServices;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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

        } catch (SQLException e) {
            connection.setAutoCommit(true);
            throw e;
        }
        return res;
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
    public boolean newItem(ItemDTO dto) {
        return false;
    }

    @Override
    public boolean newCategory(ItemCategoryDTO dto) {
        return false;
    }

    @Override
    public boolean newItemAndCategory(ItemDTO item, ItemCategoryDTO cat) {
        return false;
    }

    @Override
    public boolean updateItem(ItemDTO dto) {
        return false;
    }

    @Override
    public boolean removeItem(int id) {
        return false;
    }

    @Override
    public List<ItemCategoryDTO> getCategories() {
        return null;
    }

    @Override
    public List<ItemDTO> getItems() {
        return null;
    }
}
