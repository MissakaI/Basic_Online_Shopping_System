package com.ijse.onlineshoppingsys.service.custom;

import com.ijse.onlineshoppingsys.dto.LoginCredentialsDTO;
import com.ijse.onlineshoppingsys.dto.CustomerDTO;
import com.ijse.onlineshoppingsys.dto.ItemCategoryDTO;
import com.ijse.onlineshoppingsys.dto.ItemDTO;
import com.ijse.onlineshoppingsys.service.SuperService;

import java.util.List;

public interface AdminServices extends SuperService {
    boolean validateLogin(LoginCredentialsDTO dto);

    boolean newCustomer(CustomerDTO cust);
    boolean updateCustomer(CustomerDTO cust);
    List<CustomerDTO> viewCustomers();
    boolean removeCustomer(int cust_id);
    CustomerDTO searchCustomer(int cust_id);

    boolean newItem();
    boolean newCategory();
    boolean newItemAndCategory();
    boolean updateItem();
    boolean removeItem();
    List<ItemCategoryDTO> getCategories();
    List<ItemDTO> getItems();

}
