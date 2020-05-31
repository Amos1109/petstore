package com.petstore.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.petstore.dao.CustomerDao;
import com.petstore.dto.CustomerDTO;
import com.petstore.dto.PetDTO;
import com.petstore.dto.ShoppingCart;

public class CustomerService {
	
	CustomerDao customerDao=new CustomerDao();
	public List<Map<String, Object>>getPetListByCId(String cid){
		return customerDao.getPetListByCId(cid);
	}
	
	public List<Map<String, Object>> getPetById(String id){
		return customerDao.getPetById(id);
	}

	public List<Map<String,Object>> checkLogin(String email,String pwd){return customerDao.checkLogin(email,pwd);}

	public List<Map<String,Object>> recharge(String email,double money){return customerDao.recharge(email,money);}

	public void addOrder(ShoppingCart cart, CustomerDTO customer) {
		int oid = customerDao.addOrder(customer.getId(), String.valueOf(cart.getTotalMoney()));
		Set<Integer> pids = cart.getCartItems().keySet();
		for (Integer pid : pids) {
			String price = String.valueOf(cart.getCartItems().get(pid).getPrice());
			int quantity = cart.getCartItems().get(pid).getQuantity();
			customerDao.addOrderDetail(oid, pid, price, quantity);
		}
	}

	public List<Map<String,Object>> getOrderListByCId(String cid){
		return  customerDao.getOrderListByCId(cid);
	}
}
