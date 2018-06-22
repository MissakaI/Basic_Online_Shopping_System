package com.ijse.onlineshoppingsys.dao.custom;

import com.ijse.onlineshoppingsys.bo.CustomerBO;
import com.ijse.onlineshoppingsys.dao.SuperDAO;

import java.sql.Connection;

public interface CustomerDAO extends SuperDAO<CustomerBO,Integer> {
    void setConnection(Connection connection);
}
