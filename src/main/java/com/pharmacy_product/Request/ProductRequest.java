package com.pharmacy_product.Request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class ProductRequest {

	private String productName;
	private String description;
	private String category;
	private double price;
	private Integer stockQuantity;
	private String manufacturer;
	private LocalDate expiryDate;
	private String batchNumber;
	private boolean prescriptionRequired = false;
	private String dosage;
	private String sideEffects;
	private Long supplierId;
}