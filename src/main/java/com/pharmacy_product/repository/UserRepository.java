package com.pharmacy_product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy_product.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	boolean existsByEmail(String email);

}
