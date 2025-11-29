package com.pharmacy_product.Response;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProductResponse {

	private Long id;
	private String productName;
	private String description;
	private String category;
	private double price;
	private Integer stockQuantity;
	private String manufacturer;
	private LocalDate expiryDate;
	private String batchNumber;
	private boolean prescriptionRequired;
	private String dosage;
	private String sideEffects;
	private SupplierResponse supplier;
}
