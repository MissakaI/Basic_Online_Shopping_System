package com.ijse.onlineshoppingsys.dao.custom.impl;

import com.ijse.onlineshoppingsys.bo.CustomerLoginBO;
import com.ijse.onlineshoppingsys.dao.custom.CustomerLoginDAO;
import com.ijse.onlineshoppingsys.resource.ResourceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerLoginDAOImpl implements CustomerLoginDAO{
    private Connection connection;

    public CustomerLoginDAOImpl() throws SQLException, ClassNotFoundException {
        setConnection(ResourceFactory.getConnectionResource(ResourceFactory.ResourceConnectionType.MYSQL).getConnection());
    }

    @Override
    public int create(CustomerLoginBO custLoginBO) throws SQLException {
        String SQL="INSERT INTO customerlogin VALUES (?,PASSWORD(?),?)";
        PreparedStatement stm = getConnection().prepareStatement(SQL);
        stm.setObject(1,custLoginBO.getUsername());
        stm.setObject(2,custLoginBO.getPassword());
        stm.setObject(3,custLoginBO.getCust_id());
        int res=stm.executeUpdate();
        return res > 0 ? 0 : -1;
    }

    @Override
    public boolean update(CustomerLoginBO custLoginBO) throws SQLException {
        String SQL="UPDATE customerlogin SET PASSWORD=PASSWORD(?) WHERE USERNAME=?";
        PreparedStatement stm = getConnection().prepareStatement(SQL);
        stm.setObject(2,custLoginBO.getUsername());
        stm.setObject(1,custLoginBO.getPassword());
        int res=stm.executeUpdate();
        return res>0;
    }

    @Override
    public CustomerLoginBO fetch(String s) {
        throw new UnsupportedOperationException("Operation not supported for this Business Object");
    }

    @Override
    public List<CustomerLoginBO> readAll() {
        throw new UnsupportedOperationException("Operation not supported for this Business Object");
    }

    @Override
    public boolean delete(String s) throws SQLException {
        String SQL="DELETE FROM customerlogin WHERE CUST_ID=?";
        PreparedStatement stm = getConnection().prepareStatement(SQL);
        stm.setObject(1,s);
        int res=stm.executeUpdate();
        return res>0;
    }

    @Override
    public Integer checkCredentials(CustomerLoginBO custLoginBO) throws SQLException {
        String SQL = "SELECT CUST_PASSWORD=PASSWORD(?),CUST_ID FROM customerlogin WHERE CUST_USERNAME=?";
        PreparedStatement stm = getConnection().prepareStatement(SQL);
        stm.setObject(2,custLoginBO.getUsername());
        stm.setObject(1,custLoginBO.getPassword());
        ResultSet rst=stm.executeQuery();
        if (rst.next() && rst.getBoolean(1)) {
            return rst.getInt(2);
        }
        return -1;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
