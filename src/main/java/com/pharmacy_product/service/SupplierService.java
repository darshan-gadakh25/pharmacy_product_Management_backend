package com.pharmacy_product.service;

import com.pharmacy_product.Response.ApiResponse;
import com.pharmacy_product.Request.SupplierRequest;

public interface SupplierService {

	ApiResponse addSupplier(SupplierRequest supplier);
}