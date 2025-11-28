package com.pharmacy_product.service;

import com.pharmacy_product.Request.CustomerRequest;
import com.pharmacy_product.Response.ApiResponse;

public interface CustomerService {

	ApiResponse addCustomer(CustomerRequest customer);
}