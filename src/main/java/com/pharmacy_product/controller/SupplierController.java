package com.pharmacy_product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@PutMapping("/{id}")
	public ResponseEntity<?> updateSupplier(@PathVariable Long id, @RequestBody SupplierRequest dto) {
		try {
			return ResponseEntity.ok(supplierService.updateSupplier(id, dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), "Failed!!"));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSupplier(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(supplierService.deleteSupplier(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), "Failed!!"));
		}
	}

	@GetMapping
	public ResponseEntity<?> getAllSuppliers() {
		try {
			return ResponseEntity.ok(supplierService.getAllSuppliers());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), "Failed!!"));
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getSupplierById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(supplierService.getSupplierById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), "Failed!!"));
		}
	}
}