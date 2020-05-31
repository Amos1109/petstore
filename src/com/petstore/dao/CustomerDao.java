package com.petstore.dao;

import java.util.List;
import java.util.Map;

import com.petstore.dto.PetDTO;

public class CustomerDao extends BaseDao{
	public List<Map<String, Object>>getPetListByCId(String cid){
		if(cid==null||cid.isEmpty()) {
			String sql="select * from pet where is_offsale = 0 order by id limit 3";
			return select(sql, null);
		}else {
			String sql="select * from pet where is_offsale = 0 and c_id=? order by id limit 3";
			Object param[]= {cid};
			return select(sql, param);
		}
	}
	
	public List<Map<String, Object>> getPetById(String id){
		String sql="select * from pet where is_offsale = 0 and id=?";
		Object param[]= {id};
		return select(sql, param);
	}

	public List<Map<String,Object>> checkLogin(String email,String pwd){
		String sql="select * from customer where email= ? and pwd = ?";
		Object param[]={email,pwd};
		return select(sql,param);
	}

	public List<Map<String,Object>> recharge(String email,double Money){
		String sql="update customer set balance=balance + ? where email= ?";
		Object param[]={Money,email};
		//update(sql,param);
		updateByParams(sql,param);
		String sql2="select * from customer where email= ?";
		Object param2[]={email};
		return select(sql2,param2);
	}

	public List<Map<String,Object>> paging(String nextPage){
		String sql="select * from pet where is_offsale = 0 order by id limit ?,3";
		Object param[]={nextPage};
		return select(sql,param);
	}
}
