package com.ijse.onlineshoppingsys.resource;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionResource {
    Connection getConnection() throws ClassNotFoundException,SQLException;
}
