package com.pharmacy_product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy_product.Request.AuthRequest;
import com.pharmacy_product.Request.UserRequest;
import com.pharmacy_product.Response.ApiResponse;
import com.pharmacy_product.Response.AuthResponse;
import com.pharmacy_product.exception.ApiException;
import com.pharmacy_product.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

	private final UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<?> signUp(@RequestBody UserRequest dto){
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(dto));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(),"Failed"));
		}
		
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> userAuthentication(@RequestBody 
			AuthRequest dto) {
		System.out.println("in sign in "+dto);
		try {
		//invoke service layer method
			return ResponseEntity.ok(userService.authenticate(dto));
		} catch (RuntimeException e) {
			System.out.println("err "+e);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED) 
					.body(new ApiResponse(e.getMessage(), "Failed"));
		}
	}
	
}
