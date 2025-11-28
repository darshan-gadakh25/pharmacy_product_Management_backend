package com.pharmacy_product.Request;

import java.time.LocalDate;

import com.pharmacy_product.entities.UserEntity;
import com.pharmacy_product.entities.enums.Gender;
import com.pharmacy_product.entities.enums.UserRole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class CustomerRequest {

	private UserRequest userDetails;
	private Gender gender;
	private LocalDate dateOfBirth;
	private String altMobile;
	private Integer loyaltyPoints = 0;
}