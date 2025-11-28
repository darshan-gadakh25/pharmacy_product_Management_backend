package com.pharmacy_product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy_product.Request.UserRequest;
import com.pharmacy_product.Response.ApiResponse;
import com.pharmacy_product.exception.ApiException;
import com.pharmacy_product.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@PostMapping
	public ResponseEntity<?> signUp(@RequestBody UserRequest dto){
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(dto));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(),"Failed"));
		}
		
	}
}
