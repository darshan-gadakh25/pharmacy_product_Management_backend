package com.pharmacy_product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy_product.entities.BillingEntity;

public interface BillingRepository extends JpaRepository<BillingEntity, Long>{

}
