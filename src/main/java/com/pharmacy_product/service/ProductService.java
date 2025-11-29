package com.pharmacy_product.service;

import java.util.List;

import com.pharmacy_product.Request.ProductRequest;
import com.pharmacy_product.Response.ApiResponse;
import com.pharmacy_product.Response.ProductResponse;

public interface ProductService {

	ApiResponse addProduct(ProductRequest product);
	List<ProductResponse> getAllProducts();
	ProductResponse getProductById(Long id);
	ApiResponse updateProduct(Long id, ProductRequest product);
	ApiResponse deleteProduct(Long id);
	List<ProductResponse> searchProducts(String query);
}