package com.petstore.servlet.customer;

import com.petstore.dto.CustomerDTO;
import com.petstore.service.CustomerService;
import com.petstore.util.JsonResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@WebServlet(name = "ModifyPwdServlet", urlPatterns = {"/modifypwd"})
public class ModifyPwdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email=request.getParameter("email");
        String oldPwd=request.getParameter("oldPassword");
        String pwd=request.getParameter("password");
        JsonResult result=new JsonResult();
        CustomerService customerService=new CustomerService();
        List<Map<String, Object>> users = customerService.checkLogin(email, pwd);
        if (users.isEmpty()) {
            result.setSuccess(false);
            result.setMsg("原密码不正确！");
        } else {
            result.setSuccess(true);

            }


        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
