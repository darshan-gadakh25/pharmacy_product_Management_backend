package com.pharmacy_product.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class OrderItemResponse {
    private Long id;
    private Integer quantity;
    private double unitPrice;
    private double totalPrice;
    private OrderProductResponse product;
}
