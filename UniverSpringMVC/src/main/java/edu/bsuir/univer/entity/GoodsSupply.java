package edu.bsuir.univer.entity;

import java.util.Date;

public class GoodsSupply {

	private Integer id;
    private Date date;
    private String name;
    private Integer amount;
	private Integer price;

	public GoodsSupply() {

	}

	public GoodsSupply(Integer id, Date date, String name, Integer amount, Integer price) {
		this.id = id;
		this.date = date;
		this.name = name;
		this.amount = amount;
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
