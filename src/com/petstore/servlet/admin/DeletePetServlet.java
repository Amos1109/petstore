package com.petstore.servlet.admin;

import com.alibaba.fastjson.JSON;
import com.petstore.service.AdminService;
import com.petstore.util.JsonResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "DeletePetServlet", urlPatterns = {"/deletepet"})
public class DeletePetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        JsonResult jsonResult=new JsonResult();
        AdminService adminService=new AdminService();
        if(adminService.deletePet(id)){
            jsonResult.setSuccess(true);
        }else{
            jsonResult.setSuccess(false);
        }
        //输出Json
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");


        PrintWriter out = response.getWriter();
        String resultJson = JSON.toJSONString(jsonResult);
        out.print(resultJson);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
