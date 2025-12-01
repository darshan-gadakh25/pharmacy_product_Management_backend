package com.pharmacy_product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy_product.Request.CustomerRequest;
import com.pharmacy_product.Response.ApiResponse;
import com.pharmacy_product.exception.ApiException;
import com.pharmacy_product.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping()
	public ResponseEntity<?> addCustomer(@RequestBody CustomerRequest dto) {

		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(dto));

	}
}