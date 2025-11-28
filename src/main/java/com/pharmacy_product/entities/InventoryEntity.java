package com.pharmacy_product.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "inventory")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "inventory_id"))
@ToString(callSuper = true, exclude = {"productDetails","adminDetails"})
public class InventoryEntity extends BaseEntity {

	@Column(nullable = false)
	private Integer quantityAdded;
	
	@Column(nullable = false)
	private Integer quantityRemoved;
	
	private String reason;
	
	private LocalDateTime transactionDate;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private ProductEntity productDetails;

	@ManyToOne
	@JoinColumn(name = "admin_id")
	private AdminEntity adminDetails;
}