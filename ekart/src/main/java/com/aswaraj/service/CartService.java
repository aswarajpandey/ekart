package com.aswaraj.service;

import java.math.BigDecimal;
import java.util.Map;

import com.aswaraj.exception.NotEnoughStockException;
import com.aswaraj.model.Product;

public interface CartService {
	
	void addProductToCart(Product product);
	
	void removeProductFromCart(Product product);
	
	Map<Product, Integer> getProductsInCart();
	
	void checkOut() throws NotEnoughStockException;
	
	BigDecimal getTotal();

}
