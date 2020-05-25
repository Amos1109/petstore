package com.petstore.servlet.customer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.AddDefaultCharsetFilter;

import com.alibaba.fastjson.JSON;
import com.petstore.dto.ShoppingCart;
import com.petstore.service.ShoppingCartService;
import com.petstore.util.JsonResult;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShoppingCartService shoppingCartService=new ShoppingCartService();
		ShoppingCart cart;
		//��session��ȡ���ﳵ����cart
		//���cartΪnull;��һ�������½�һ�����ﳵ����
		if(request.getSession().getAttribute("cart")==null) {
			cart=new ShoppingCart();
			request.getSession().setAttribute("cart", cart);
		}else {
			cart=(ShoppingCart)request.getSession().getAttribute("cart");
		}
		
		//��ȡ��������get/add/remove/modify
		//Ĭ��Ϊ"get"
		String type="get";
		if(request.getParameter("type")!=null) {
			type=request.getParameter("type");
		}
		String id=request.getParameter("id");
		String quantity=request.getParameter("quantity");
		JsonResult result=new JsonResult();
		result.setSuccess(true);
		switch(type){
	        case "get":
	        request.getRequestDispatcher("cart.jsp").forward(request, response);
	        break;
	        case "add":
	    		if(id==null) {
	    			result.setMsg("��Ʒid��������ȷ!");
	    			result.setSuccess(false);
	    		}
	    		if(quantity==null) {
	    			result.setMsg("��Ʒ������������ȷ!");
	    			result.setSuccess(false);
	    		}
	    		if(result.getSuccess()==true) {
	    			//����ShoppingCartService,����ҵ��
	    			cart=shoppingCartService.addToCart(Integer.parseInt(id),Integer.parseInt(quantity),cart);
	    			result.setMsg("���빺�ﳵ���!");
	    			result.setMsg2(Integer.toString(cart.getTotalCount()));
	    		}
			 break;
			case "remove":
			if(id==null) {
				result.setMsg("��Ʒid��������ȷ!");
				result.setSuccess(false);
			}
			if(result.getSuccess()==true) {
				//����ShoppingCartService,����ҵ��
				cart=shoppingCartService.removeFromCart(Integer.parseInt(id), cart);
				result.setMsg("ɾ����Ʒ���!");
				result.setMsg2(Integer.toString(cart.getTotalCount()));
				result.setMsg3(Double.toString(cart.getTotalMoney()));
			}
			 break;
			case "modify":
				int quantityInt=Integer.parseInt(quantity);
				int idInt=Integer.parseInt(id);
			if(quantityInt<=0) {
				result.setMsg("��Ʒ��Ŀ����С�ڵ���0����");
				result.setSuccess(false);
			}
			if(result.getSuccess()==true) {
				cart=shoppingCartService.modifyCart(idInt, quantityInt, cart);
				result.setMsg(Double.toString(cart.getCartItems().get(idInt).getSubTotal()));
				result.setMsg2(Integer.toString(cart.getTotalCount()));
				result.setMsg3(Double.toString(cart.getTotalMoney()));
	     }
			 break;
	    }
		
		
		//���Json
		response.setContentType("text/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
	

		PrintWriter out=response.getWriter();
		String resultJson=JSON.toJSONString(result);
		out.print(resultJson);
		out.flush();
		out.close();
		

	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}
