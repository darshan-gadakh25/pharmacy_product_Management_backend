package com.pharmacy_product.service;

import com.pharmacy_product.Request.UserRequest;
import com.pharmacy_product.Response.ApiResponse;

public interface UserService {

	ApiResponse addUser(UserRequest user);
}
