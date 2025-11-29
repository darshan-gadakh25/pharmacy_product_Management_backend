package com.pharmacy_product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy_product.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	boolean existsByProductName(String productName);
	
	List<ProductEntity> findByProductNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(String name, String category);
}