package com.petstore.servlet.admin;

import com.alibaba.fastjson.JSON;
import com.petstore.dto.CustomerDTO;
import com.petstore.dto.PetDTO;
import com.petstore.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "PetListServlet", urlPatterns = {"/petlist"})
public class PetListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String pageStr=request.getParameter("page");
            String limitStr=request.getParameter("limit");
            int page=Integer.parseInt(pageStr);
            int limit=Integer.parseInt(limitStr);
            CustomerService customerService = new CustomerService();
            List<Map<String,Object>> petList=customerService.getPetList(page,limit);
            List<PetDTO>pets=new ArrayList<>();
            for( int i = 0; i < petList.size(); i++ ) {
                PetDTO pet=new PetDTO(petList.get(i));
                pets.add(pet);
            }

            String PetJson= JSON.toJSONString(pets);
            StringBuffer sb = new StringBuffer();
            sb.append("{\"count\":");
            sb.append(40);
            sb.append(",\"code\":");
            sb.append(0);
            sb.append(",\"msg\":");
            sb.append("null");
            sb.append(",\"data\":");
            sb.append(PetJson);
            sb.append("}");
            //输出Json
            response.setContentType("text/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");

            PrintWriter out = response.getWriter();
            out.print(sb.toString());
            out.flush();
            out.close();
        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
