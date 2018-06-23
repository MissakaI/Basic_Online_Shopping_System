package com.ijse.onlineshoppingsys.dao.custom;

import com.ijse.onlineshoppingsys.bo.ItemCategoryBO;
import com.ijse.onlineshoppingsys.dao.SuperDAO;

import java.sql.Connection;

public interface ItemCategoryDAO extends SuperDAO<ItemCategoryBO, Integer> {
    void setConnection(Connection connection);
}
