package com.ijse.onlineshoppingsys.dao.custom;

import com.ijse.onlineshoppingsys.bo.ItemBO;
import com.ijse.onlineshoppingsys.dao.SuperDAO;

import java.sql.Connection;

public interface ItemDAO extends SuperDAO<ItemBO,Integer> {
    void setConnection(Connection connection);
}
