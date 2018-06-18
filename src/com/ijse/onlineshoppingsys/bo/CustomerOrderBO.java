package com.ijse.onlineshoppingsys.bo;

import java.time.LocalDate;

public class CustomerOrderBO {
    private int orderId;
    private LocalDate orderDate;
    private int custId;

    public CustomerOrderBO(int orderId, LocalDate orderDate, int cust_id) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.custId = cust_id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }
}
