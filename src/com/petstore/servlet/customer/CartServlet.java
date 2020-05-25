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
		//从session中取购物车对象cart
		//如果cart为null;第一次请求，新建一个购物车对象
		if(request.getSession().getAttribute("cart")==null) {
			cart=new ShoppingCart();
			request.getSession().setAttribute("cart", cart);
		}else {
			cart=(ShoppingCart)request.getSession().getAttribute("cart");
		}
		
		//获取请求类型get/add/remove/modify
		//默认为"get"
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
	    			result.setMsg("商品id参数不正确!");
	    			result.setSuccess(false);
	    		}
	    		if(quantity==null) {
	    			result.setMsg("商品数量参数不正确!");
	    			result.setSuccess(false);
	    		}
	    		if(result.getSuccess()==true) {
	    			//调用ShoppingCartService,处理业务
	    			cart=shoppingCartService.addToCart(Integer.parseInt(id),Integer.parseInt(quantity),cart);
	    			result.setMsg("加入购物车完成!");
	    			result.setMsg2(Integer.toString(cart.getTotalCount()));
	    		}
			 break;
			case "remove":
			if(id==null) {
				result.setMsg("商品id参数不正确!");
				result.setSuccess(false);
			}
			if(result.getSuccess()==true) {
				//调用ShoppingCartService,处理业务
				cart=shoppingCartService.removeFromCart(Integer.parseInt(id), cart);
				result.setMsg("删除商品完成!");
				result.setMsg2(Integer.toString(cart.getTotalCount()));
				result.setMsg3(Double.toString(cart.getTotalMoney()));
			}
			 break;
			case "modify":
				int quantityInt=Integer.parseInt(quantity);
				int idInt=Integer.parseInt(id);
			if(quantityInt<=0) {
				result.setMsg("商品数目不得小于等于0件！");
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
		
		
		//输出Json
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
