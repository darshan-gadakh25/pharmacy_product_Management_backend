package com.pharmacy_product.entities;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "product_id"))
@ToString(callSuper = true)
public class ProductEntity extends BaseEntity {

	@Column(nullable = false)
	private String productName;
	
	private String description;

	@Column(nullable = false)
	private String category;
	
	@Column(nullable = false)
	private double price;
	
	@Column(nullable = false)
	private Integer stockQuantity;
	
	private String manufacturer;
	
	private LocalDate expiryDate;
	
	private String batchNumber;
	
	private boolean prescriptionRequired = false;
	
	private String dosage;
	
	private String sideEffects;

	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private SupplierEntity supplier;
}