package com.ijse.onlineshoppingsys.dao;

import java.sql.SQLException;
import java.util.List;

public interface SuperDAO<T, K> {
    int create(T t) throws SQLException;

    boolean update(T t) throws SQLException;

    T fetch(K k) throws SQLException;

    List<T> readAll() throws SQLException;

    boolean delete(K k) throws SQLException;
}
