package com.petstore.servlet.customer;

import com.petstore.dto.CustomerDTO;
import com.petstore.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CustomerServlet",urlPatterns = {"/customer"})
public class CustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email=request.getParameter("inputUserName");
        String pwd=request.getParameter("inputPassword");
        CustomerService customerService=
                new CustomerService();
        List<Map<String,Object>> users=customerService.checkLogin(email,pwd);
        if(users.isEmpty()){
            request.setAttribute("logMsg", "用户名或密码错误！");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }else{
            Map<String, Object> user= users.get(0);
            request.getSession().setAttribute("user",getCustomByMap(user));
            response.sendRedirect(request.getContextPath()+"/index");
        }


    }

    private CustomerDTO getCustomByMap(Map<String , Object> map){
        CustomerDTO customer = new CustomerDTO();
        customer.setBalance(((BigDecimal)map.get("balance")).doubleValue());
        customer.setName((String) map.get("name"));
        customer.setId((long)map.get("id"));
        customer.setEmail((String)map.get("email"));
        return customer;
    }
}
