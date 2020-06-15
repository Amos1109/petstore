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
        List<Map<String, Object>> users = customerService.checkLogin(email,oldPwd);
        if (users.isEmpty()) {
            result.setSuccess(false);
            result.setMsg("原密码不正确！");
        } else {
            if( customerService.modifyPwd(email,pwd)){
                result.setSuccess(true);
                result.setMsg("密码修改成功！");
            }else {
                result.setSuccess(false);
                result.setMsg("密码修改失败！");
            }

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
        doPost(request,response);
    }
}
