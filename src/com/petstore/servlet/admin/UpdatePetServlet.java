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

@WebServlet(name = "UpdatePetServlet", urlPatterns = {"/updatepet"})
public class UpdatePetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        String tag = request.getParameter("tag");
        String description = request.getParameter("description");
        String cid = request.getParameter("cid");
        String price = request.getParameter("price");
        String stock = request.getParameter("stock");
        AdminService adminService=new AdminService();
        JsonResult result = new JsonResult();
        if(adminService.updatePet(id,name,tag,description,cid,price,stock)){
            result.setSuccess(true);
            result.setMsg("更新宠物信息成功！");
        }else{
            result.setSuccess(false);
            result.setMsg("更新宠物信息失败！");
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
