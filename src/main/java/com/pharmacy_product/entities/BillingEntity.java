package com.pharmacy_product.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "billing")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "billing_id"))
@ToString(callSuper = true, exclude = {"order"})
public class BillingEntity extends BaseEntity {

    @Column(nullable = false)
    private String cardNumber;
    
    @Column(nullable = false)
    private String cardHolderName;
    
    @Column(nullable = false)
    private String expiryMonth;
    
    @Column(nullable = false)
    private String expiryYear;
    
    @Column(nullable = false)
    private String cvv;
    
    @Column(nullable = false)
    private String billingAddress;
    
    private String paymentStatus; // PENDING, SUCCESS, FAILED
    
    private String transactionId;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;
}