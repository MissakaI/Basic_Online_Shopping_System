package com.ijse.onlineshoppingsys.dao.custom.impl;

import com.ijse.onlineshoppingsys.bo.CustomerBO;
import com.ijse.onlineshoppingsys.dao.custom.CustomerDAO;
import com.ijse.onlineshoppingsys.resource.ResourceFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    Connection connection;

    public CustomerDAOImpl() throws SQLException, ClassNotFoundException {
        connection=ResourceFactory.getConnectionResource(ResourceFactory.ResourceConnectionType.MYSQL).getConnection();
    }

    @Override
    public boolean create(CustomerBO customerBO) throws SQLException {
        String SQL="INSERT INTO customer VALUES (?,?,?)";
        PreparedStatement stm=connection.prepareStatement(SQL);
        stm.setObject(1,customerBO.getName());
        stm.setObject(2,customerBO.getNic());
        stm.setObject(3,customerBO.getMobile());
        int res=stm.executeUpdate();
        return res>0;
    }

    @Override
    public boolean update(CustomerBO customerBO) throws SQLException {
        String SQL="UPDATE customer SET NAME=?,NIC=?,MOBILE=? WHERE CUST_ID=?";
        PreparedStatement stm=connection.prepareStatement(SQL);
        stm.setObject(1,customerBO.getName());
        stm.setObject(2,customerBO.getNic());
        stm.setObject(3,customerBO.getMobile());
        stm.setObject(4,customerBO.getCust_id());
        int res=stm.executeUpdate();
        return res>0;
    }

    @Override
    public CustomerBO fetch(Integer custId) throws SQLException {
        String SQL="SELECT * FROM customer WHERE CUST_ID=?";
        PreparedStatement stm=connection.prepareStatement(SQL);
        stm.setObject(1, custId);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            return new CustomerBO(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getInt(4)
            );
        }
        return null;
    }

    @Override
    public List<CustomerBO> readAll() throws SQLException {
        String SQL="SELECT * FROM customer";
        PreparedStatement stm=connection.prepareStatement(SQL);
        ResultSet rst=stm.executeQuery();
        List<CustomerBO> list=new ArrayList<>();
        while(rst.next()){
            list.add(
                    new CustomerBO(
                            rst.getInt(1),
                            rst.getString(2),
                            rst.getInt(3),
                            rst.getInt(4)
                    )
            );
        }
        return list;
    }

    @Override
    public boolean delete(Integer custId) throws SQLException {
        String SQL="DELETE FROM customer WHERE CUST_ID=?";
        PreparedStatement stm=connection.prepareStatement(SQL);
        stm.setObject(1,custId);
        int res=stm.executeUpdate();
        return res>0;
    }
}
