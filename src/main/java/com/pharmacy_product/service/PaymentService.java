package com.pharmacy_product.service;

import com.pharmacy_product.Request.CreditCardRequest;
import com.pharmacy_product.Response.PaymentResponse;

public interface PaymentService {
    boolean validateCreditCard(CreditCardRequest creditCard);
    PaymentResponse processPayment(CreditCardRequest creditCard, double amount);
}