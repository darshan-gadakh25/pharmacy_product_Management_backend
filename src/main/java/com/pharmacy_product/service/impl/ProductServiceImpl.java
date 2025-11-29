package com.pharmacy_product.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pharmacy_product.Request.ProductRequest;
import com.pharmacy_product.Response.ApiResponse;
import com.pharmacy_product.Response.ProductResponse;
import com.pharmacy_product.entities.ProductEntity;
import com.pharmacy_product.entities.SupplierEntity;
import com.pharmacy_product.exception.ApiException;
import com.pharmacy_product.repository.ProductRepository;
import com.pharmacy_product.repository.SupplierRepository;
import com.pharmacy_product.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final SupplierRepository supplierRepository;
	private final ModelMapper mapper;

	@Override
	public ApiResponse addProduct(ProductRequest productRequest) {

		if (productRepository.existsByProductName(productRequest.getProductName())) {
			throw new ApiException("Product already exists with this name");
		}

		ProductEntity product = mapper.map(productRequest, ProductEntity.class);

		if (productRequest.getSupplierId() != null) {
			SupplierEntity supplier = supplierRepository.findById(productRequest.getSupplierId())
					.orElseThrow(() -> new ApiException("Supplier not found"));
			product.setSupplier(supplier);
		}

		productRepository.save(product);

		return new ApiResponse("Product added successfully!!!", "Success");
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		return productRepository.findAll().stream()
				.map(product -> mapper.map(product, ProductResponse.class))
				.toList();
	}

	@Override
	public ProductResponse getProductById(Long id) {
		ProductEntity product = productRepository.findById(id)
				.orElseThrow(() -> new ApiException("Product not found"));
		return mapper.map(product, ProductResponse.class);
	}

	@Override
	public ApiResponse updateProduct(Long id, ProductRequest productRequest) {
		ProductEntity product = productRepository.findById(id)
				.orElseThrow(() -> new ApiException("Product not found"));
		
		mapper.map(productRequest, product);
		
		if (productRequest.getSupplierId() != null) {
			SupplierEntity supplier = supplierRepository.findById(productRequest.getSupplierId())
					.orElseThrow(() -> new ApiException("Supplier not found"));
			product.setSupplier(supplier);
		}
		
		productRepository.save(product);
		return new ApiResponse("Product updated successfully", "Success");
	}

	@Override
	public ApiResponse deleteProduct(Long id) {
		if (!productRepository.existsById(id)) {
			throw new ApiException("Product not found");
		}
		productRepository.deleteById(id);
		return new ApiResponse("Product deleted successfully", "Success");
	}

	@Override
	public List<ProductResponse> searchProducts(String query) {
		return productRepository.findByProductNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(query, query)
				.stream()
				.map(product -> mapper.map(product, ProductResponse.class))
				.toList();
	}
}