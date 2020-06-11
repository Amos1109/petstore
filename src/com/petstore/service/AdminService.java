package com.petstore.service;

import com.petstore.dao.AdminDao;

public class AdminService {
    AdminDao adminDao=new AdminDao();
    public boolean updatePet(String id,String name,String tag,String description,String cid,String price,String stock){
        return adminDao.updatePet(id,name,tag,description,cid,price,stock);
    }
}
