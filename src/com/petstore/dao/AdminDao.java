package com.petstore.dao;

public class AdminDao extends BaseDao{
    public boolean updatePet(String id,String name,String tag,String description,String cid,String price,String stock,String weight,String img){
        String sql="update pet SET name=?,tag=?,description=?,c_id=?,price=?,stock=?,weight=?,img=? where id=?";
        Object param[]={name,tag,description,cid,price,stock,weight,img,id};
        return updateByParams(sql,param);
    }

    public boolean insertPet(String name,String tag,String description,String cid,String price,String stock,String weight,String img,String onSaleTime){
        String sql="insert into pet values(null,?,?,?,?,?,?,?,?,0,?,0)";
        Object param[]={cid,name,tag,img,price,stock,description,weight,onSaleTime};
        return updateByParams(sql,param);
    }

    public boolean deletePet(String id){
        String sql="delete from pet where id=?";
        Object param[]={id};
        return updateByParams(sql,param);
    }
}
