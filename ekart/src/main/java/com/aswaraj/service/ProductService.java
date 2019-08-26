package com.aswaraj.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aswaraj.model.Product;

/**
 * @author Aswaraj
 *
 */
public interface ProductService {

	Optional<Product> findById(Long id);
	
	Page<Product> findAllProductsPageable(Pageable pageable);
	
}
