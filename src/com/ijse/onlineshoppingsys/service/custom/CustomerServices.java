package com.ijse.onlineshoppingsys.service.custom;

import com.ijse.onlineshoppingsys.dto.*;
import com.ijse.onlineshoppingsys.service.SuperService;

import java.util.List;

public interface CustomerServices extends SuperService {

    CustomerDTO validateLogin(LoginCredentialsDTO login);

    List<ItemCategoryDTO> getCategories();
    List<ItemDTO> getItems();
    List<ItemDTO> getItemOfCategory(int catId);

    boolean addToCart(CartItemDTO item);
    boolean removeCartItem(int itemId);
    boolean clearCart();
    ReceiptDTO purchaseCart();
    ReceiptDTO purchaseSelectedItems();


}
