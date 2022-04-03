package com.kidzoo.catalogue.model;

public enum StockStatusEnum {
	AVAILABLE("available"),
	BACKORDER("backorder"),
	OUTOFSTOCK("outofstock");

	private String stockStatus;
	
	StockStatusEnum(String stock) {
		this.stockStatus=stock;
	}

	public String getStockStatus() {
		return stockStatus;
	}
}
