package com.pharmacy_product.Response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.pharmacy_product.entities.enums.OrderStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class OrderResponse {

    private Long id;
    private double totalAmount;
    private OrderStatus status;
    private LocalDateTime orderDate;
    private String deliveryAddress;
    private String paymentMethod;
    private Long customerId;
    private List<OrderItemResponse> orderItems;

  
}