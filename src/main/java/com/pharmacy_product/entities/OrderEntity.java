package com.pharmacy_product.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.pharmacy_product.entities.enums.OrderStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "order_id"))
@ToString(callSuper = true , exclude = {"customer","orderItems"})
public class OrderEntity extends BaseEntity {

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal totalAmount;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private OrderStatus status;
	
	private LocalDateTime orderDate;
	
	private String deliveryAddress;
	
	private String paymentMethod;
	
	private String prescriptionImage;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private CustomerEntity customer;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderItemEntity> orderItems;
}