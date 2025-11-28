package com.pharmacy_product.entities;

import com.pharmacy_product.entities.enums.UserRole;

import jakarta.persistence.*;

import lombok.*;


@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@ToString(callSuper = true)
public class UserEntity extends BaseEntity {


    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String mobile;

    private String street;

    private String city;

    private String state;

    private String pincode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role=UserRole.CUSTOMER;;

    private boolean active = true;

   
}
