package com.jpmorgan.stockmarket.model;

import com.jpmorgan.stockmarket.enums.TradeType;

import java.sql.Timestamp;

public class Trade {

	private Integer id;
	private TradeType type;
	private Integer quantity;
	private Double price;
	private Timestamp dateSell;

	public Trade() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TradeType getType() {
		return type;
	}

	public void setType(TradeType type) {
		this.type = type;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Trade(TradeType type, Integer quantity, Double price) {
		this.setType(type);
		this.setQuantity(quantity);
		this.setPrice(price);
	}

	public Timestamp getDateSell() {
		return dateSell;
	}

	public void setDateSell(Timestamp dateSell) {
		this.dateSell = dateSell;
	}
}
