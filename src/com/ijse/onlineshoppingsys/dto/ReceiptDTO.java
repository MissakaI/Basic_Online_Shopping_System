package com.ijse.onlineshoppingsys.dto;

import java.util.ArrayList;
import java.util.List;

public class ReceiptDTO {
    CustomerDTO customer;
    List<CartItemDTO> itemList=new ArrayList<>();
    double totalAmount;
}
