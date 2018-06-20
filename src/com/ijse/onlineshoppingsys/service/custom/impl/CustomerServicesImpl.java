package com.ijse.onlineshoppingsys.service.custom.impl;

import com.ijse.onlineshoppingsys.dto.*;
import com.ijse.onlineshoppingsys.service.custom.CustomerServices;

import java.util.List;

public class CustomerServicesImpl implements CustomerServices {
    @Override
    public CustomerDTO validateLogin(LoginCredentialsDTO login) {
        return null;
    }

    @Override
    public List<ItemCategoryDTO> getCategories() {
        return null;
    }

    @Override
    public List<ItemDTO> getItems() {
        return null;
    }

    @Override
    public List<ItemDTO> getItemOfCategory(int catId) {
        return null;
    }

    @Override
    public boolean addToCart(CartItemDTO item) {
        return false;
    }

    @Override
    public boolean removeCartItem(int itemId) {
        return false;
    }

    @Override
    public boolean clearCart() {
        return false;
    }

    @Override
    public ReceiptDTO purchaseCart() {
        return null;
    }

    @Override
    public ReceiptDTO purchaseSelectedItems() {
        return null;
    }
}
