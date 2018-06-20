package com.ijse.onlineshoppingsys.dao;



import com.ijse.onlineshoppingsys.dao.custom.impl.*;

import java.sql.SQLException;

public class DAOFactory {
    public enum DAOType{ADMIN_LOGIN,CUSTOMER_LOGIN,ITEM_CATEGORY,ITEM,CUSTOMER}

    public static <T extends SuperDAO> T getDAO(DAOType type) throws SQLException, ClassNotFoundException {
        switch (type){
            case ITEM:return (T) new ItemDAOImpl();
            case CUSTOMER:return (T) new CustomerDAOImpl();
            case ADMIN_LOGIN:return (T) new AdminLoginDAOImpl();
            case ITEM_CATEGORY:return (T) new ItemCategoryDAOImpl();
            case CUSTOMER_LOGIN:return (T) new CustomerLoginDAOImpl();
        }
        return null;
    }
}
