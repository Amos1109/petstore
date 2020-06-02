package com.petstore.servlet.customer;

import com.petstore.dto.CustomerDTO;
import com.petstore.dto.ShoppingCart;
import com.petstore.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "OrderServlet", urlPatterns = {"/order"})
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
        CustomerDTO customer = (CustomerDTO) request.getSession().getAttribute("user");
        if (customer == null) {
            response.sendRedirect("login.jsp");
        } else {
            CustomerService customerService = new CustomerService();
            customerService.addOrder(cart, customer);
            List<Map<String,Object>> orderList=customerService.getOrderPetByCId(String.valueOf(customer.getId()));
            Map<String,Object>orderTotal=customerService.getOrderListByCId(String.valueOf(customer.getId())).get(0);
            request.setAttribute("orderList",orderList);
            request.setAttribute("orderTotal",orderTotal);
            request.getRequestDispatcher("order.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


}
