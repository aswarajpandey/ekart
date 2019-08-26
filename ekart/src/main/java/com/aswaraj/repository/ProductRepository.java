package com.aswaraj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aswaraj.model.Product;

/**
 * @author Aswaraj
 *
 */
public interface ProductRepository extends JpaRepository<Product, Long>{

	Optional<Product> findById(Long id);
}
