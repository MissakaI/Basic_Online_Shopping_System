package com.ijse.onlineshoppingsys.dao.custom.impl;

import com.ijse.onlineshoppingsys.bo.ItemCategoryBO;
import com.ijse.onlineshoppingsys.dao.custom.ItemCategoryDAO;
import com.ijse.onlineshoppingsys.resource.ResourceFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemCategoryDAOImpl implements ItemCategoryDAO {
    Connection connection;

    public ItemCategoryDAOImpl() throws SQLException, ClassNotFoundException {
        connection=ResourceFactory.getConnectionResource(ResourceFactory.ResourceConnectionType.MYSQL).getConnection();
    }

    @Override
    public int create(ItemCategoryBO itemCategoryBO) throws SQLException {
        String SQL = String.format("INSERT INTO itemcategory VALUES ('%s')", itemCategoryBO.getCategory());
        Statement stm = connection.createStatement();
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
    public boolean update(ItemCategoryBO itemCategoryBO) throws SQLException {
        String SQL="UPDATE itemcategory SET CATEGORY=? WHERE CAT_ID=?";
        PreparedStatement stm=connection.prepareStatement(SQL);
        stm.setObject(1,itemCategoryBO.getCategory());
        stm.setObject(2,itemCategoryBO.getCat_id());
        int rst=stm.executeUpdate();
        return rst>0;
    }

    @Override
    public ItemCategoryBO fetch(Integer catID) throws SQLException {
        String SQL="SELECT * FROM itemcategory WHERE CAT_ID=?";
        PreparedStatement stm=connection.prepareStatement(SQL);
        stm.setObject(1,catID);
        ResultSet rst=stm.executeQuery();
        if (rst.next()){
            return new ItemCategoryBO(
                    rst.getInt(1),
                    rst.getString(2)
            );
        }
        return null;
    }

    @Override
    public List<ItemCategoryBO> readAll() throws SQLException {
        String SQL="SELECT * FROM itemcategory";
        PreparedStatement stm=connection.prepareStatement(SQL);
        ResultSet rst=stm.executeQuery();
        List<ItemCategoryBO> list=new ArrayList<>();
        while(rst.next()){
            list.add(
                    new ItemCategoryBO(
                            rst.getInt(1),
                            rst.getString(2)
                    )
            );
        }
        return list;
    }

    @Override
    public boolean delete(Integer catID) throws SQLException {
        String SQL="SELECT * FROM itemcategory WHERE CAT_ID=?";
        PreparedStatement stm=connection.prepareStatement(SQL);
        stm.setObject(1,catID);
        int rst=stm.executeUpdate();
        return rst>0;
    }
}
