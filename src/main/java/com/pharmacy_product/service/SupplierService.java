package com.pharmacy_product.service;

import java.util.List;

import com.pharmacy_product.Response.ApiResponse;
import com.pharmacy_product.Request.SupplierRequest;
import com.pharmacy_product.entities.SupplierEntity;

public interface SupplierService {

	ApiResponse addSupplier(SupplierRequest supplier);
	ApiResponse updateSupplier(Long id, SupplierRequest supplier);
	ApiResponse deleteSupplier(Long id);
	List<SupplierEntity> getAllSuppliers();
	SupplierEntity getSupplierById(Long id);
}