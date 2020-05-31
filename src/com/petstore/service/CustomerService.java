package com.petstore.service;

import java.util.List;
import java.util.Map;

import com.petstore.dao.CustomerDao;
import com.petstore.dto.PetDTO;

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
}
