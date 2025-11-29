package com.pharmacy_product.Request;

import lombok.*;

@Getter
@Setter
@ToString
public class AuthRequest {

	private String email;
	private String password;
}
