package com.petstore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.petstore.dto.PetDTO;
import com.petstore.util.Pager;

public class CustomerDao extends BaseDao{
	public List<Map<String, Object>>getPetList(int page,int limit){
			int first=(page-1)*10;
			Object param[]={first,limit};
			String sql="select * from pet order by id limit ?,? ";
			return select(sql, param);
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

	public int addOrder(long cid,String money){
		String sql="insert into `order` values(null,?,?,?,now())";
		Object param[]={cid,money,"未付款"};
		String sql1="select LAST_INSERT_ID() from `order`";
		return  getLastId(sql,sql1,param);
	}

	public boolean addOrderDetail(int oid,int pid,String price,int quantity){
		String sql="insert into orderdetail values(null,?,?,?,?)";
		Object param[]={oid,pid,price,quantity};
		return updateByParams(sql,param);
	}

	public List<Map<String,Object>> getOrderPetByOId(String oid){
		String sql="select name,img,orderdetail.price,quantity from `order`,orderdetail,pet where oid=? and `order`.id=orderdetail.oid and orderdetail.pid=pet.id";
		Object param[]= {oid};
		return select(sql, param);
	}

	public List<Map<String,Object>>getOrderListByCId(String cid){
		String sql="select *  from `order` where cid= ? order by id desc;";
		Object param[]={cid};
		return select2(sql,param);
	}



	public Pager<PetDTO>findPet(PetDTO petModel,int pageNum,int pageSize)  {
		Pager<PetDTO> result=null;
		List<Object> paramList=new ArrayList<Object>();
		String name=petModel.getName();
		String cid=Integer.toString(petModel.getC_id());

		StringBuilder sql=new StringBuilder("select * from pet where 1=1");
		StringBuilder countSql = new StringBuilder(
				"select count(id) as totalRecord from pet where 1=1 ");

		if(name!=null&&!name.equals("")){
			sql.append(" and name like ?");
			countSql.append(" and name like ?");
			paramList.add("%" + name + "%");
		}
		if(!cid.equals("0")){
			sql.append(" and c_id= ?");
			countSql.append(" and c_id= ?");
			paramList.add(cid);
		}

		// 起始索引
		int fromIndex	= pageSize * (pageNum -1);

		// 使用limit关键字，实现分页
		sql.append(" limit " + fromIndex + ", " + pageSize );

		// 存放所有查询出的宠物对象
		List<PetDTO> petList = new ArrayList<PetDTO>();

		// 获取总记录数
		List<Map<String, Object>> countResult = findResult(countSql.toString(), paramList);
		Map<String, Object> countMap = countResult.get(0);
		int totalRecord = ((Number)countMap.get("totalRecord")).intValue();

		// 获取查询的宠物记录
		List<Map<String, Object>> petResult = findResult(sql.toString(), paramList);
		if (petResult != null) {
			for (Map<String, Object> map : petResult) {
				PetDTO s = new PetDTO(map);
				petList.add(s);
			}
		}
		//获取总页数
		int totalPage = totalRecord / pageSize;
		if(totalRecord % pageSize !=0){
			totalPage++;
		}

		// 组装pager对象
		result = new Pager<PetDTO>(pageSize, pageNum,
				totalRecord, totalPage, petList);

		return result;
	}


	public int totalRecord(){
		List<Object> paramList=new ArrayList<Object>();
		String sql="select count(id) as totalRecord from pet";
		List<Map<String, Object>> countResult = findResult(sql,paramList);
		Map<String, Object> countMap = countResult.get(0);
		return ((Number)countMap.get("totalRecord")).intValue();
	}
}
