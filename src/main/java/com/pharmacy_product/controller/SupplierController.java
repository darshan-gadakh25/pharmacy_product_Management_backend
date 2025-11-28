package com.pharmacy_product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy_product.Request.SupplierRequest;
import com.pharmacy_product.Response.ApiResponse;
import com.pharmacy_product.exception.ApiException;
import com.pharmacy_product.service.SupplierService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/suppliers")
@RequiredArgsConstructor
public class SupplierController {

	private final SupplierService supplierService;
	
	@PostMapping
	public ResponseEntity<?> addSupplier(@RequestBody SupplierRequest dto){
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.addSupplier(dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(),"Failed!!"));
		}
	}
}