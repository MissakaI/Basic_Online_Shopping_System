package com.ijse.onlineshoppingsys.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceiptDTO {
    private CustomerDTO customer;
    private Map<Integer, CartItemDTO> itemMap = new HashMap();
    private double totalAmount;

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public Map<Integer, CartItemDTO> getItemMap() {
        return itemMap;
    }

    public void setItemMap(Map<Integer, CartItemDTO> itemMap) {
        this.itemMap = itemMap;
    }

    public List<CartItemDTO> getItemList() {
        return (List<CartItemDTO>) itemMap.values();
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
