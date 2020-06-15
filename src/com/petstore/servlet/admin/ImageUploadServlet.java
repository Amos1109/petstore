package com.petstore.servlet.admin;

import com.alibaba.fastjson.JSON;
import com.petstore.util.JsonResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;


@WebServlet(name = "ImageUploadServlet", urlPatterns = {"/imageupload"})
@MultipartConfig(maxFileSize = 10*1024*1024)
public class ImageUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part=request.getPart("file");
        String fileName=part.getSubmittedFileName();
        File uploadFileDir=new File(getServletContext().getRealPath("/petImg"));
        if(!uploadFileDir.exists()){
            uploadFileDir.mkdir();
        }
        String name= UUID.randomUUID().toString()+fileName.substring(fileName.lastIndexOf("."));
        part.write(uploadFileDir+File.separator+name);
        JsonResult jsonResult=new JsonResult();
        jsonResult.setMsg(name);
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
