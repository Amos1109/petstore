package com.petstore.servlet.customer;

import com.alibaba.fastjson.JSON;
import com.petstore.dto.CustomerDTO;
import com.petstore.service.CustomerService;
import com.petstore.util.JsonResult;

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

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("inputUserName");
        String pwd = request.getParameter("inputPassword");
        CustomerService customerService = new CustomerService();
        List<Map<String, Object>> users = customerService.checkLogin(email, pwd);
        JsonResult result=new JsonResult();
        if (users.isEmpty()) {
           result.setSuccess(false);
           result.setMsg("用户名或密码错误！");
        } else {
            Map<String, Object> user = users.get(0);
            request.getSession().setAttribute("user", getCustomByMap(user));
            result.setSuccess(true);
        }
        //输出Json
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String resultJson = JSON.toJSONString(result);
        out.print(resultJson);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
