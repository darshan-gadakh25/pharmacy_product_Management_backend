package com.pharmacy_product.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pharmacy_product.Request.AuthRequest;
import com.pharmacy_product.Request.UserRequest;
import com.pharmacy_product.Response.ApiResponse;
import com.pharmacy_product.Response.AuthResponse;
import com.pharmacy_product.config.JwtUtil;
import com.pharmacy_product.entities.UserEntity;
import com.pharmacy_product.entities.enums.UserRole;
import com.pharmacy_product.exception.ApiException;
import com.pharmacy_product.repository.CustomerRepository;
import com.pharmacy_product.repository.UserRepository;
import com.pharmacy_product.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final CustomerRepository customerRepository;
	private final ModelMapper mapper;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	@Override
	public ApiResponse addUser(UserRequest userRequest) {
		if(userRepository.existsByEmail(userRequest.getEmail())) {
			throw new ApiException("User already exist with this Email");
		}
		
		UserEntity user=mapper.map(userRequest, UserEntity.class);
		user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		UserEntity userEntity=userRepository.save(user);
		
		return new ApiResponse("User Register Successfully!!!","Success");
	}

	@Override
	public AuthResponse authenticate(AuthRequest request) {
		UserEntity user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new ApiException("Invalid Email or Password"));
		
		boolean passwordMatches = false;
		if (user.getPassword().startsWith("$2a$") || user.getPassword().startsWith("$2b$")) {
			passwordMatches = passwordEncoder.matches(request.getPassword(), user.getPassword());
		} else {
			passwordMatches = request.getPassword().equals(user.getPassword());
		}
		
		if (!passwordMatches) {
			throw new ApiException("Invalid Email or Password");
		}
		
		String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
		
		AuthResponse auth = mapper.map(user, AuthResponse.class);
		auth.setToken(token);
		auth.setMessage("Login Successful");
		return auth;
	}

}
