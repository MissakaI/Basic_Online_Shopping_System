package com.ijse.onlineshoppingsys.service.custom;

import com.ijse.onlineshoppingsys.dto.*;
import com.ijse.onlineshoppingsys.service.SuperService;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public interface CustomerServices extends SuperService {

    CustomerDTO validateLogin(LoginCredentialsDTO login) throws SQLException, ClassNotFoundException;

    List<ItemCategoryDTO> getCategories() throws SQLException, ClassNotFoundException;

    List<ItemDTO> getItems() throws SQLException, ClassNotFoundException;
    List<ItemDTO> getItemOfCategory(int catId);

    boolean addToCart(HttpSession session, CartItemDTO item) throws SQLException, ClassNotFoundException;

    boolean removeCartItem(HttpSession session, int itemId);

    boolean clearCart(HttpSession session);

    ReceiptDTO purchaseCart(HttpSession session);
    ReceiptDTO purchaseSelectedItems();


}
