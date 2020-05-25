package com.petstore.dto;

import java.util.Date;

public class PetDTO {
	private long id;
	private int c_id;
	private String name;
	private String tag;
	private String img;
	private double price;
	private int stock;
	private String description;
	private int weight;
	private int score;
	private boolean offsale;
	private Date onSaleTime;
	
	public PetDTO() {
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public boolean isOffsale() {
		return offsale;
	}
	public void setOffsale(boolean offsale) {
		this.offsale = offsale;
	}
	public Date getOnSaleTime() {
		return onSaleTime;
	}
	public void setOnSaleTime(Date onSaleTime) {
		this.onSaleTime = onSaleTime;
	}
}
