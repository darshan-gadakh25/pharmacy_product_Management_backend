package com.pharmacy_product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy_product.entities.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

}