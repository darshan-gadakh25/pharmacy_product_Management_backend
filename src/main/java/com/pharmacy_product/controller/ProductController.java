package com.pharmacy_product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy_product.Request.ProductRequest;
import com.pharmacy_product.Response.ApiResponse;
import com.pharmacy_product.Response.ProductResponse;
import com.pharmacy_product.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@PostMapping()
	public ResponseEntity<?> addProduct(@RequestBody ProductRequest dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(dto));
	}
	
	@GetMapping()
	public ResponseEntity<?> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Long id) {
		return ResponseEntity.ok(productService.getProductById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductRequest dto) {
		return ResponseEntity.ok(productService.updateProduct(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
		return ResponseEntity.ok(productService.deleteProduct(id));
	}

	@GetMapping("/search")
	public ResponseEntity<?> searchProducts(@RequestParam String query) {
		return ResponseEntity.ok(productService.searchProducts(query));
	}
}