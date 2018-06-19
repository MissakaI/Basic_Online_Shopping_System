package com.ijse.onlineshoppingsys.resource;

import com.ijse.onlineshoppingsys.resource.impl.MySQLConnectionResourceImpl;

public class ResourceFactory {
    public enum ResourceConnectionType{
        MYSQL
    }

    public static ConnectionResource getConnectionResource(ResourceConnectionType type){
        switch (type){
            case MYSQL:return new MySQLConnectionResourceImpl();
        }
        return null;
    }
}
