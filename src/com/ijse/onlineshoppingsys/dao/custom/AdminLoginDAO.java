package com.ijse.onlineshoppingsys.dao.custom;

import com.ijse.onlineshoppingsys.bo.AdminLoginBO;
import com.ijse.onlineshoppingsys.dao.SuperDAO;

import java.sql.SQLException;

public interface AdminLoginDAO extends SuperDAO<AdminLoginBO,String> {
    boolean checkCredentials(AdminLoginBO adminLoginBO) throws SQLException;
}
