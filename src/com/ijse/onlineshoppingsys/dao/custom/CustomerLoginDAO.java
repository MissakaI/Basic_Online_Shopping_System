package com.ijse.onlineshoppingsys.dao.custom;

import com.ijse.onlineshoppingsys.bo.CustomerLoginBO;
import com.ijse.onlineshoppingsys.dao.SuperDAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface CustomerLoginDAO extends SuperDAO<CustomerLoginBO,String> {
    Integer checkCredentials(CustomerLoginBO custLoginBO) throws SQLException;

    void setConnection(Connection connection);
}
