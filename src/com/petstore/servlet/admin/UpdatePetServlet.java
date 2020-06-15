package com.petstore.servlet.admin;

import com.alibaba.fastjson.JSON;
import com.petstore.service.AdminService;
import com.petstore.util.JsonResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        String weight = request.getParameter("weight");
        String imgSrc=request.getParameter("img");
        File tempFile =new File( imgSrc.trim());
        String fileName = tempFile.getName();
        AdminService adminService=new AdminService();
        JsonResult result = new JsonResult();
        if(id==null||id.isEmpty()){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
            String onSaleTime=df.format(new Date());
            if(adminService.insertPet(name,tag,description,cid,price,stock,weight,fileName,onSaleTime)){
                result.setSuccess(true);
                result.setMsg("添加宠物信息成功！");
            }else{
                result.setSuccess(false);
                result.setMsg("添加宠物信息失败！");
            }
        }else{
            if(adminService.updatePet(id,name,tag,description,cid,price,stock,weight,fileName)){
                result.setSuccess(true);
                result.setMsg("更新宠物信息成功！");
            }else{
                result.setSuccess(false);
                result.setMsg("更新宠物信息失败！");
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
