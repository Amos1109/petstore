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

@WebServlet(name = "NewOrderServlet", urlPatterns = {"/neworder"})
public class NewOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
        CustomerDTO customer = (CustomerDTO) request.getSession().getAttribute("user");
        if (customer == null) {
            response.sendRedirect("login.jsp");
        } else {
            CustomerService customerService = new CustomerService();
            if(cart!=null){
                customerService.addOrder(cart, customer);
            }
            List<Map<String,Object>> orderList=customerService.getOrderListByCId(String.valueOf(customer.getId()));
            request.setAttribute("orderList",orderList);
            request.getRequestDispatcher("order.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
