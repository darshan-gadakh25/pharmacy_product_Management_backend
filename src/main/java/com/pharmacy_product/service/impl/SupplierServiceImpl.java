package com.pharmacy_product.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pharmacy_product.Request.SupplierRequest;
import com.pharmacy_product.Response.ApiResponse;
import com.pharmacy_product.entities.SupplierEntity;
import com.pharmacy_product.entities.enums.UserRole;
import com.pharmacy_product.exception.ApiException;
import com.pharmacy_product.repository.SupplierRepository;
import com.pharmacy_product.repository.UserRepository;
import com.pharmacy_product.service.SupplierService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

	private final SupplierRepository supplierRepository;
	private final UserRepository userRepository;
	private final ModelMapper mapper;

	@Override
	public ApiResponse addSupplier(SupplierRequest supplierRequest) {

		if (userRepository.existsByEmail(supplierRequest.getUserDetails().getEmail())) {
			throw new ApiException("Supplier already exist with this Email");
		}

		SupplierEntity supplier = mapper.map(supplierRequest, SupplierEntity.class);
		supplier.getUserDetails().setRole(UserRole.SUPPLIER);

		supplierRepository.save(supplier);

		return new ApiResponse("Supplier Register Successfully!!!", "Success");
	}
}