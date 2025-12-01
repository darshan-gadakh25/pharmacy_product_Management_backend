package com.pharmacy_product.Request;

import java.util.List;

import com.pharmacy_product.entities.enums.PaymentMethod;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class OrderPaymentRequest {
    private double totalAmount;
    private String deliveryAddress;
    private PaymentMethod paymentMethod;
    private String prescriptionImage;
    private Long customerId;
    private List<OrderItemRequest> orderItems;
    private CreditCardRequest creditCard; // Only required if paymentMethod is CREDIT_CARD
}