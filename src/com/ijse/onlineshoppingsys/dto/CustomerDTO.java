package com.ijse.onlineshoppingsys.dto;

public class CustomerDTO {
    private int cust_id;
    private String name;
    private int nic;
    private int mobile;
    private LoginCredentialsDTO login;

    public CustomerDTO(int cust_id, String name, int nic, int mobile) {
        this.cust_id = cust_id;
        this.name = name;
        this.nic = nic;
        this.mobile = mobile;
    }

    public CustomerDTO(int cust_id, String name, int nic, int mobile, LoginCredentialsDTO login) {
        this.cust_id = cust_id;
        this.name = name;
        this.nic = nic;
        this.mobile = mobile;
        this.setLogin(login);
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


    public LoginCredentialsDTO getLogin() {
        return login;
    }

    public void setLogin(LoginCredentialsDTO login) {
        this.login = login;
    }
}
