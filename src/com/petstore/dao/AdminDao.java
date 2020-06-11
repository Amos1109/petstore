package com.petstore.dao;

public class AdminDao extends BaseDao{
    public boolean updatePet(String id,String name,String tag,String description,String cid,String price,String stock){
        String sql="update pet SET name=?,tag=?,description=?,c_id=?,price=?,stock=? where id=?";
        Object param[]={name,tag,description,cid,price,stock,id};
        return updateByParams(sql,param);
    }
}
