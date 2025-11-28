package com.pharmacy_product.Request;

import com.pharmacy_product.entities.enums.UserRole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class UserRequest {

	private String fullName;
	private String email;
	private String password;
	private String mobile;
	private String street;
	private String city;
	private String state;
	private String pincode;
	private UserRole role;
}
