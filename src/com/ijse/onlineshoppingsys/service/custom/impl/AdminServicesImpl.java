package com.ijse.onlineshoppingsys.service.custom.impl;

import com.ijse.onlineshoppingsys.dto.LoginCredentialsDTO;
import com.ijse.onlineshoppingsys.dto.CustomerDTO;
import com.ijse.onlineshoppingsys.dto.ItemCategoryDTO;
import com.ijse.onlineshoppingsys.dto.ItemDTO;
import com.ijse.onlineshoppingsys.service.custom.AdminServices;

import java.util.List;

public class AdminServicesImpl implements AdminServices {
    @Override
    public boolean validateLogin(LoginCredentialsDTO dto) {
        return false;
    }

    @Override
    public boolean newCustomer(CustomerDTO cust) {
        return false;
    }

    @Override
    public boolean updateCustomer(CustomerDTO cust) {
        return false;
    }

    @Override
    public List<CustomerDTO> viewCustomers() {
        return null;
    }

    @Override
    public boolean removeCustomer(int cust_id) {
        return false;
    }

    @Override
    public CustomerDTO searchCustomer(int cust_id) {
        return null;
    }

    @Override
    public boolean newItem() {
        return false;
    }

    @Override
    public boolean newCategory() {
        return false;
    }

    @Override
    public boolean newItemAndCategory() {
        return false;
    }

    @Override
    public boolean updateItem() {
        return false;
    }

    @Override
    public boolean removeItem() {
        return false;
    }

    @Override
    public List<ItemCategoryDTO> getCategories() {
        return null;
    }

    @Override
    public List<ItemDTO> getItems() {
        return null;
    }
}
