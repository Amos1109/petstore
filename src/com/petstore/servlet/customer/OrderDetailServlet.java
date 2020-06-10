package com.petstore.servlet.customer;

import com.alibaba.fastjson.JSON;
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

@WebServlet(name = "OrderDetailServlet", urlPatterns = {"/orderdetail"})
public class OrderDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonResult result=new JsonResult();
        result.setSuccess(true);
        String id=request.getParameter("id");
        if(id==null){
            result.setMsg("订单id参数不正确！");
            result.setSuccess(false);
        }
        if(result.getSuccess()){
            CustomerService customerService=new CustomerService();
            List<Map<String,Object>>orderDetailList=customerService.getOrderPetByOId(id);
            String msgString="";
            for(Map<String,Object>map:orderDetailList){
                String price=map.get("price").toString();
                double subTotal=Double.parseDouble(price)*(int)map.get("quantity");
                msgString+=String.format("<tr><td><img src='petImg/%s' width='80'></td><td>%s</td><td><span>%s</span></td><td><span>%s</span></td><td><span>%s</span></td></tr>",
                       map.get("img"),map.get("name"),map.get("price"),map.get("quantity"),subTotal);
            }
            result.setMsg(msgString);
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
