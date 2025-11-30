package com.pharmacy_product.Response;

import com.pharmacy_product.entities.enums.UserRole;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class AuthResponse {
	private Long id;
	private String fullName;
	private String email;
	private String mobile;
	private UserRole role;
	private String message;
	private String token;
}