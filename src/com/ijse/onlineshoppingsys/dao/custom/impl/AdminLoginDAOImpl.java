package com.ijse.onlineshoppingsys.dao.custom.impl;

import com.ijse.onlineshoppingsys.bo.AdminLoginBO;
import com.ijse.onlineshoppingsys.dao.custom.AdminLoginDAO;
import com.ijse.onlineshoppingsys.resource.ResourceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminLoginDAOImpl implements AdminLoginDAO {
    Connection connection;

    public AdminLoginDAOImpl() throws SQLException, ClassNotFoundException {
        connection=ResourceFactory.getConnectionResource(ResourceFactory.ResourceConnectionType.MYSQL).getConnection();
    }

    @Override
    public int create(AdminLoginBO adminLoginBO) throws SQLException {
        String SQL="INSERT INTO adminlogin VALUES (?,PASSWORD(?))";
        PreparedStatement stm=connection.prepareStatement(SQL);
        stm.setObject(1,adminLoginBO.getUsername());
        stm.setObject(2,adminLoginBO.getPassword());
        int res=stm.executeUpdate();
        return res > 0 ? 0 : -1;
    }

    @Override
    public boolean update(AdminLoginBO adminLoginBO) throws SQLException {
        String SQL="UPDATE adminlogin SET PASSWORD=PASSWORD(?) WHERE USERNAME=?";
        PreparedStatement stm=connection.prepareStatement(SQL);
        stm.setObject(2,adminLoginBO.getUsername());
        stm.setObject(1,adminLoginBO.getPassword());
        int res=stm.executeUpdate();
        return res>0;
    }

    @Override
    public AdminLoginBO fetch(String s) throws SQLException {
        throw new UnsupportedOperationException("Operation not supported for this Business Object");
    }

    @Override
    public List<AdminLoginBO> readAll() throws SQLException {
        throw new UnsupportedOperationException("Operation not supported for this Business Object");
    }

    @Override
    public boolean delete(String s) throws SQLException {
        String SQL="DELETE FROM adminlogin WHERE USERNAME=?";
        PreparedStatement stm=connection.prepareStatement(SQL);
        stm.setObject(1,s);
        int res=stm.executeUpdate();
        return res>0;
    }

    @Override
    public boolean checkCredentials(AdminLoginBO adminLoginBO) throws SQLException {
        String SQL="SELECT PASSWORD=PASSWORD(?) FROM adminlogin WHERE USERNAME=?";
        PreparedStatement stm=connection.prepareStatement(SQL);
        stm.setObject(2,adminLoginBO.getUsername());
        stm.setObject(1,adminLoginBO.getPassword());
        ResultSet rst=stm.executeQuery();
        if(rst.next())
            return rst.getBoolean(1);
        return false;
    }
}
