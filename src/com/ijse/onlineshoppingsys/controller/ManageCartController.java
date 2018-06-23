package com.ijse.onlineshoppingsys.controller;

import com.ijse.onlineshoppingsys.dto.CartItemDTO;
import com.ijse.onlineshoppingsys.dto.ReceiptDTO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

@WebServlet(name = "ManageCartController", urlPatterns = "/manageCartController")
public class ManageCartController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    public Collection<CartItemDTO> getItemsInCart(HttpSession session) {
        ReceiptDTO receipt = (ReceiptDTO) session.getAttribute("receipt");
        if (receipt != null) {
            return receipt.getItemMap().values();
        }
        return null;
    }
}
