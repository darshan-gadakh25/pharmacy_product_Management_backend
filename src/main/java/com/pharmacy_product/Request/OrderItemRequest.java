package com.pharmacy_product.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class OrderItemRequest {
    private Integer quantity;
    private Long productId;
}