package com.petstore.dto;

import java.util.HashMap;

public class ShoppingCart {
	private HashMap<Integer, CartItem> cartItems;
	private int totalCount=0;
	private double totalMoney;
	
	public ShoppingCart() {
		cartItems = new HashMap<Integer, CartItem>();
	}
	public String getCountAndTotalJsonString() {
		return String.format("{\"totalCount\":\"%s\",\"totalMoney\":\"%s\"}",getTotalCount(),getTotalMoney());
	}
	public HashMap<Integer, CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(HashMap<Integer, CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
	
}
