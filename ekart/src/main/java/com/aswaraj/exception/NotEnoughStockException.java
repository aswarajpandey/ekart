package com.aswaraj.exception;

import com.aswaraj.model.Product;

public class NotEnoughStockException extends Exception{

	private static final String DEFAULT_MESSAGE = "Not enough products in stock.";

	public NotEnoughStockException() {
		super(DEFAULT_MESSAGE);
	}


	public NotEnoughStockException(Product product) {
		super(String.format("Requested quantity for %s is currently not in stock. Currently available quantity is %d.", product.getName(), product.getQuantity()));
	}


	
	
	
}
