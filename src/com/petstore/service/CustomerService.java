package com.petstore.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.petstore.dao.CustomerDao;
import com.petstore.dto.CustomerDTO;
import com.petstore.dto.PetDTO;
import com.petstore.dto.ShoppingCart;
import com.petstore.util.Pager;

public class CustomerService {
	
	CustomerDao customerDao=new CustomerDao();
	//获取宠物首页列表
	public List<Map<String, Object>>getPetList(int page,int limit){ return customerDao.getPetList(page,limit); }

	//获取商店宠物详情
	public List<Map<String, Object>> getPetById(String id){
		return customerDao.getPetById(id);
	}

	//登录检查
	public List<Map<String,Object>> checkLogin(String email,String pwd){return customerDao.checkLogin(email,pwd);}

	//充值
	public List<Map<String,Object>> recharge(String email,double money){return customerDao.recharge(email,money);}


	//添加订单
	public void addOrder(ShoppingCart cart, CustomerDTO customer) {
		int oid = customerDao.addOrder(customer.getId(), String.valueOf(cart.getTotalMoney()));
		Set<Integer> pids = cart.getCartItems().keySet();
		for (Integer pid : pids) {
			String price = String.valueOf(cart.getCartItems().get(pid).getPrice());
			int quantity = cart.getCartItems().get(pid).getQuantity();
			customerDao.addOrderDetail(oid, pid, price, quantity);
		}
	}

	//获取订单摘要
	public List<Map<String,Object>> getOrderListByCId(String cid){
		return  customerDao.getOrderListByCId(cid);
	}

	//获取订单宠物详情
	public List<Map<String,Object>>getOrderPetByOId(String oid){ return customerDao.getOrderPetByOId(oid);}

	//分页
	public Pager<PetDTO> findPet(PetDTO petModel, int pageNum,int pageSize)  { return customerDao.findPet(petModel,pageNum,pageSize); }
}
