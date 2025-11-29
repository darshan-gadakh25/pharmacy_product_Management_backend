package com.pharmacy_product.Response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SupplierResponse {

	private String companyName;

	private String companyAddress;

	private String companyEmail;

	private String companyPhone;

	private String supplyCategory;
}
