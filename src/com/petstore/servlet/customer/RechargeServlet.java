package com.petstore.servlet.customer;

import com.petstore.dto.CustomerDTO;
import com.petstore.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@WebServlet(name = "RechargeServlet", urlPatterns = {"/recharge"})
public class RechargeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        double money = Double.parseDouble(request.getParameter("money"));

        CustomerService customerService = new CustomerService();
        List<Map<String, Object>> users = customerService.recharge(email, money);
        if (users.isEmpty()) {
            request.setAttribute("errorMsg", "��ֵʧ�ܣ�");
            request.getRequestDispatcher("recharge.jsp").forward(request, response);

        } else {
            Map<String, Object> user = users.get(0);
            request.getSession().setAttribute("user", getCustomByMap(user));
            request.setAttribute("successMsg", "��ֵ�ɹ���");
            request.getRequestDispatcher("balance.jsp").forward(request, response);

        }
    }

    private CustomerDTO getCustomByMap(Map<String, Object> map) {
        CustomerDTO customer = new CustomerDTO();
        customer.setBalance(((BigDecimal) map.get("balance")).doubleValue());
        customer.setName((String) map.get("name"));
        customer.setId((long) map.get("id"));
        customer.setEmail((String) map.get("email"));
        return customer;
    }
}
