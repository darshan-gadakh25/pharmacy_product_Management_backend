package com.pharmacy_product.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pharmacy_product.Request.CreditCardRequest;
import com.pharmacy_product.Response.PaymentResponse;
import com.pharmacy_product.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public boolean validateCreditCard(CreditCardRequest creditCard) {
        // Basic validation
        if (creditCard.getCardNumber() == null || creditCard.getCardNumber().length() < 16) {
            return false;
        }
        if (creditCard.getCvv() == null || creditCard.getCvv().length() != 3) {
            return false;
        }
        if (creditCard.getExpiryMonth() == null || creditCard.getExpiryYear() == null) {
            return false;
        }
        
        // Simulate card validation (in real scenario, use payment gateway)
        return !creditCard.getCardNumber().startsWith("0000");
    }

    @Override
    public PaymentResponse processPayment(CreditCardRequest creditCard, double amount) {
        if (!validateCreditCard(creditCard)) {
            return new PaymentResponse("FAILED", "Invalid card details", null);
        }
        
        // Simulate payment processing
        String transactionId = "TXN_" + UUID.randomUUID().toString().substring(0, 8);
        
        // Simulate success/failure (90% success rate)
        boolean paymentSuccess = Math.random() > 0.1;
        
        if (paymentSuccess) {
            return new PaymentResponse("SUCCESS", "Payment processed successfully", transactionId);
        } else {
            return new PaymentResponse("FAILED", "Payment processing failed", null);
        }
    }
}