package com.pharmacy_product.Request;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class OrderRequest {

    private double totalAmount;
    private String deliveryAddress;
    private String paymentMethod;
    private String prescriptionImage;
    private Long customerId;
    private List<OrderItemRequest> orderItems;

   
}