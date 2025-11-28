package com.pharmacy_product.entities;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "order_items")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "order_item_id"))
@ToString(callSuper = true)
public class OrderItemEntity extends BaseEntity {

	@Column(nullable = false)
	private Integer quantity;

	@Column(nullable = false)
	private double unitPrice;

	@Column(nullable = false)
	private double totalPrice;

	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private OrderEntity order;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private ProductEntity product;
}