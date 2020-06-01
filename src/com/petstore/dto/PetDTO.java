package com.petstore.dto;

import java.util.Date;
import java.util.Map;

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

	public PetDTO(Map<String, Object> map){
		this.id = (long)map.get("id");
		this.c_id = (int)map.get("c_id");
		this.name = (String) map.get("name");
		this.tag = (String) map.get("tag");
		this.img = (String)map.get("img");
		this.price = Double.parseDouble(map.get("price").toString());
		this.stock = (int)map.get("stock");
		this.description = (String)map.get("description");
		this.weight = (int)map.get("weight");
		this.score = (int)map.get("score");
		this.offsale = (boolean)map.get("is_offsale");
		this.onSaleTime = (Date) map.get("onSaleTime");

	}

	public PetDTO(long id, int c_id, String name, String tag, String img, double price, int stock, String description, int weight, int score, boolean offsale, Date onSaleTime) {
		super();
		this.id = id;
		this.c_id = c_id;
		this.name = name;
		this.tag = tag;
		this.img = img;
		this.price = price;
		this.stock = stock;
		this.description = description;
		this.weight = weight;
		this.score = score;
		this.offsale = offsale;
		this.onSaleTime = onSaleTime;
	}

	public PetDTO() {
		super();
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
