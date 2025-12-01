package com.pharmacy_product.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private String status; // SUCCESS, FAILED, PENDING
    private String message;
    private String transactionId;
}