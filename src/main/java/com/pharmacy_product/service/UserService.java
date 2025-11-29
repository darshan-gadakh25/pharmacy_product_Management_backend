package com.pharmacy_product.service;

import java.util.List;

import com.pharmacy_product.Request.AuthRequest;
import com.pharmacy_product.Request.UserRequest;
import com.pharmacy_product.Response.ApiResponse;
import com.pharmacy_product.Response.AuthResponse;

public interface UserService {

	ApiResponse addUser(UserRequest user);

	AuthResponse authenticate(AuthRequest dto);

}
