package com.ijse.onlineshoppingsys.service;



import com.ijse.onlineshoppingsys.dao.SuperDAO;
import com.ijse.onlineshoppingsys.dao.custom.impl.*;
import com.ijse.onlineshoppingsys.service.custom.impl.AdminServicesImpl;
import com.ijse.onlineshoppingsys.service.custom.impl.CustomerServicesImpl;

import java.sql.SQLException;

public class ServiceFactory {
    public enum ServiceType{ADMIN,CUSTOMER}

    public static <T extends SuperService> T getService(ServiceType type){
        switch (type){
            case ADMIN:return (T) new AdminServicesImpl();
            case CUSTOMER:return (T) new CustomerServicesImpl();
        }
        return null;
    }
}
