package com.ijse.onlineshoppingsys.resource.impl;

import com.ijse.onlineshoppingsys.resource.ConnectionResource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionResourceImpl implements ConnectionResource {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/ShoppingCart","root","root");
        return conn;
    }
}
