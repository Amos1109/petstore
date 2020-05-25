package com.petstore.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.petstore.dto.*;

public class ShoppingCartService {
	
	//��ӹ���������ﳵ�ķ���
	public ShoppingCart addToCart(CartItem item,ShoppingCart cart) {
		
		int id=item.getId();
		if(cart.getCartItems().containsKey(id)) {
			item.setQuantity(item.getQuantity()+cart.getCartItems().get(id).getQuantity());
			item.setSubTotal(item.getSubTotal()+cart.getCartItems().get(id).getSubTotal());
		}
		cart.getCartItems().put(id,item);
		return refreshTotalCountAndTotalMoney(cart);
	}
	
	public ShoppingCart addToCart(int id,int quantity,ShoppingCart cart) {
		CartItem item=new CartItem();
		item.setId(id);
		item.setQuantity(quantity);
		
		CustomerService customerService=new CustomerService();
		List<Map<String, Object>>list=customerService.getPetById(String.valueOf(id));
		item.setName(list.get(0).get("name").toString());
		item.setImg(list.get(0).get("img").toString());
		item.setPrice(Double.parseDouble(list.get(0).get("price").toString()));
		item.setSubTotal(quantity*item.getPrice());
		
		return addToCart(item, cart);
	}
	
	//ɾ��������ķ���
	public ShoppingCart removeFromCart(int id,ShoppingCart cart) {
		cart.getCartItems().remove(id);
		return refreshTotalCountAndTotalMoney(cart);
	}
	
	//�޸Ĺ�����ķ���
	public ShoppingCart modifyCart(int id,int quantity,ShoppingCart cart) {
		//session����ȡcart���޸���ֵ
		cart.getCartItems().get(id).setQuantity(quantity);
		cart.getCartItems().get(id).setSubTotal
		(quantity*cart.getCartItems().get(id).getPrice());
		//����ͳ���ܽ�������
		return refreshTotalCountAndTotalMoney(cart);
	}


	
	//ͳ���ܽ�� ������
	private ShoppingCart refreshTotalCountAndTotalMoney(ShoppingCart cart) {
		// TODO Auto-generated method stub
		double totalMoney=0.0;
		int totalCount=0;
		
		Collection<CartItem>list=cart.getCartItems().values();
		Iterator<CartItem> it=list.iterator();
		while(it.hasNext()) {
			//��������
			CartItem item=it.next();
			totalMoney+=item.getPrice()*item.getQuantity();
			totalCount+=item.getQuantity();
		}
		cart.setTotalCount(totalCount);
		cart.setTotalMoney(totalMoney);
		return cart;
	}
}
