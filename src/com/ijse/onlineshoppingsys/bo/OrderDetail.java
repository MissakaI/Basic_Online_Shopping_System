package com.ijse.onlineshoppingsys.bo;

public class OrderDetail {
    private int id;
    private int orderId;
    private int itemId;
    private int qty;
    private double tot_amount;

    public OrderDetail(int id, int orderId, int itemId, int qty, double tot_amount) {
        this.id = id;
        this.orderId = orderId;
        this.itemId = itemId;
        this.qty = qty;
        this.tot_amount = tot_amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTot_amount() {
        return tot_amount;
    }

    public void setTot_amount(double tot_amount) {
        this.tot_amount = tot_amount;
    }
}
