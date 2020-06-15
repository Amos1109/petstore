package com.petstore.service;

import com.petstore.dao.AdminDao;

public class AdminService {
    AdminDao adminDao=new AdminDao();
    public boolean updatePet(String id,String name,String tag,String description,String cid,String price,String stock,String weight,String img){
        return adminDao.updatePet(id,name,tag,description,cid,price,stock,weight,img);
    }

    public boolean insertPet(String name,String tag,String description,String cid,String price,String stock,String weight,String img,String onSaleTime){
        return adminDao.insertPet(name,tag,description,cid,price,stock,weight,img,onSaleTime);
    }

    public boolean deletePet(String id){
        return adminDao.deletePet(id);
    }
}
