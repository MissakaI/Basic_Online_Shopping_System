package com.ijse.onlineshoppingsys.dao.custom.impl;

import com.ijse.onlineshoppingsys.bo.ItemBO;
import com.ijse.onlineshoppingsys.dao.custom.ItemDAO;
import com.ijse.onlineshoppingsys.resource.ResourceFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    private Connection connection;

    public ItemDAOImpl() throws SQLException, ClassNotFoundException {
        setConnection(ResourceFactory.getConnectionResource(ResourceFactory.ResourceConnectionType.MYSQL).getConnection());
    }

    @Override
    public int create(ItemBO itemBO) throws SQLException {
        String SQL = String.format("INSERT INTO item (ITEM_NAME,QTY,UNIT_PRICE,CAT_ID) VALUES  (?,?,?,?)", itemBO.getName(), itemBO.getQty(), itemBO.getUnitPrice(), itemBO.getCat_id());
        Statement stm = getConnection().createStatement();
        int res = stm.executeUpdate(SQL, Statement.RETURN_GENERATED_KEYS);
        try (ResultSet rst = stm.getGeneratedKeys()) {
            if (rst.next()) {
                return rst.getInt(1);
            }
        } catch (SQLException e) {
            throw e;
        }
        return -1;
    }

    @Override
    public boolean update(ItemBO itemBO) throws SQLException {
        String SQL="UPDATE item SET ITEM_NAME=?, QTY=?, UNIT_PRICE=?, CAT_ID=? WHERE ITEM_ID=?";
        PreparedStatement stm = getConnection().prepareStatement(SQL);
        stm.setObject(1,itemBO.getName());
        stm.setObject(2,itemBO.getQty());
        stm.setObject(3,itemBO.getUnitPrice());
        stm.setObject(4,itemBO.getCat_id());
        stm.setObject(5,itemBO.getId());
        int res=stm.executeUpdate();
        return res>0;
    }

    @Override
    public ItemBO fetch(Integer itemID) throws SQLException {
        String SQL="SELECT * FROM item WHERE ITEM_ID=?";
        PreparedStatement stm = getConnection().prepareStatement(SQL);
        stm.setObject(1,itemID);
        ResultSet rst=stm.executeQuery();
        if (rst.next()){
            return new ItemBO(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4),
                    rst.getInt(5)
            );
        }
        return null;
    }

    @Override
    public List<ItemBO> readAll() throws SQLException {
        String SQL="SELECT * FROM item";
        PreparedStatement stm = getConnection().prepareStatement(SQL);
        ResultSet rst=stm.executeQuery();
        List<ItemBO> list=new ArrayList<>();
        while (rst.next()){
            list.add(new ItemBO(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4),
                    rst.getInt(5)
            ));
        }
        return list;
    }

    @Override
    public boolean delete(Integer itemId) throws SQLException {
        String SQL="DELETE FROM item WHERE ITEM_ID=?";
        PreparedStatement stm = getConnection().prepareStatement(SQL);
        stm.setObject(1,itemId);
        int res=stm.executeUpdate();
        return res>0;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
