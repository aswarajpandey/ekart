package com.aswaraj.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aswaraj.model.Product;
import com.aswaraj.repository.ProductRepository;
import com.aswaraj.service.ProductService;

/**
 * @author Aswaraj
 *
 */
@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
	
	@Autowired
	public  ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}
	
	
	@Override
	public Page<Product> findAllProductsPageable(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
}
