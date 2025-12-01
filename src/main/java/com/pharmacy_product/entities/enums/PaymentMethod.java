package com.pharmacy_product.entities.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PaymentMethod {
    CASH_ON_DELIVERY("Cash on Delivery"),
    CREDIT_CARD("Credit Card");
    
    private final String value;
    
    PaymentMethod(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}