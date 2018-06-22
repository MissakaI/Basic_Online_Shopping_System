package com.ijse.onlineshoppingsys.service.custom;

import com.ijse.onlineshoppingsys.dto.LoginCredentialsDTO;
import com.ijse.onlineshoppingsys.dto.CustomerDTO;
import com.ijse.onlineshoppingsys.dto.ItemCategoryDTO;
import com.ijse.onlineshoppingsys.dto.ItemDTO;
import com.ijse.onlineshoppingsys.service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface AdminServices extends SuperService {
    boolean validateLogin(LoginCredentialsDTO dto) throws SQLException, ClassNotFoundException;

    boolean newCustomer(CustomerDTO cust) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO cust) throws SQLException, ClassNotFoundException;

    List<CustomerDTO> viewCustomers() throws SQLException, ClassNotFoundException;

    boolean removeCustomer(int cust_id) throws SQLException, ClassNotFoundException;

    CustomerDTO searchCustomer(int cust_id) throws SQLException, ClassNotFoundException;

    boolean newItem(ItemDTO dto);

    boolean newCategory(ItemCategoryDTO dto);

    boolean newItemAndCategory(ItemDTO item, ItemCategoryDTO cat);

    boolean updateItem(ItemDTO dto);

    boolean removeItem(int id);
    List<ItemCategoryDTO> getCategories();
    List<ItemDTO> getItems();

}
