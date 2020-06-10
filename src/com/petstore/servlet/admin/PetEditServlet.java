package com.petstore.servlet.admin;

import com.petstore.dto.PetDTO;
import com.petstore.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@WebServlet(name = "PetEditServlet", urlPatterns = {"/petedit"})
public class PetEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        CustomerService customerService =new CustomerService();
        Map<String, Object> pet = customerService.getPetById(id).get(0);
        PetDTO petDTO=new PetDTO(pet);
        request.setAttribute("pet", petDTO);
        request.getRequestDispatcher("petedit.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
