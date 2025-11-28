package com.pharmacy_product.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class SupplierRequest {

	private UserRequest userDetails;
	private String companyName;
	private String companyAddress;
	private String companyEmail;
	private String companyPhone;
	private String supplyCategory;
}