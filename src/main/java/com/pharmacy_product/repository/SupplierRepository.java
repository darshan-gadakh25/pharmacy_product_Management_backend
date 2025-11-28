package com.pharmacy_product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy_product.entities.SupplierEntity;

public interface SupplierRepository extends JpaRepository<SupplierEntity, Long> {

}