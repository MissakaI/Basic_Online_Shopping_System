package com.ijse.onlineshoppingsys.bo;

public class CustomerBO {
    private int cust_id;
    private String name;
    private int nic;
    private int mobile;

    public CustomerBO(int cust_id, String name, int nic, int mobile) {
        this.cust_id = cust_id;
        this.name = name;
        this.nic = nic;
        this.mobile = mobile;
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNic() {
        return nic;
    }

    public void setNic(int nic) {
        this.nic = nic;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }
}
