package com.aswaraj.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.aswaraj.exception.NotEnoughStockException;
import com.aswaraj.model.Product;
import com.aswaraj.repository.ProductRepository;
import com.aswaraj.service.CartService;

/**
 * @author Aswaraj
 *
 */
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class CartServiceImpl implements CartService {

	private final ProductRepository productRepository;

	private Map<Product, Integer> productsInCart = new HashMap<Product, Integer>();

	@Autowired
	public CartServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	/**
	 * If product is already exists into the Map, then increases by 1. If
	 * productsInCart is not exists into the Map, then insert 1 as quantity.
	 * 
	 * @param product
	 */
	@Override
	public void addProductToCart(Product product) {
		if (productsInCart.containsKey(product)) {
			productsInCart.replace(product, productsInCart.get(product) + 1);
		} else {
			productsInCart.put(product, 1);
		}

	}

	@Override
	public void removeProductFromCart(Product product) {
		if (productsInCart.containsKey(product)) {
			if (productsInCart.get(product) > 1) {
				productsInCart.replace(product, productsInCart.get(product) - 1);
			} else {
				productsInCart.remove(product);
			}
		}
	}

	/**
	 * @return Read only copy of productsInCart Map
	 */
	@Override
	public Map<Product, Integer> getProductsInCart() {
		return Collections.unmodifiableMap(productsInCart);
	}

	/**
	 * @throws NotEnoughStockException
	 */
	@Override
	public void checkOut() throws NotEnoughStockException {
		Product productInStock;
		for (Map.Entry<Product, Integer> productInCart : productsInCart.entrySet()) {
			productInStock = productRepository.findById(productInCart.getKey().getId()).get();
			if (productInStock.getQuantity() < productInCart.getValue()) {
				throw new NotEnoughStockException(productInStock);
			} else {
				productInCart.getKey().setQuantity(productInStock.getQuantity() - productInCart.getValue());
			}
		}
		productRepository.saveAll(productsInCart.keySet());
		productRepository.flush();
		productsInCart.clear();

	}

	@Override
	public BigDecimal getTotal() {
		return productsInCart.entrySet().stream()
				.map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}

}
