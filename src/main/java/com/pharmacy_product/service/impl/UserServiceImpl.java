package com.pharmacy_product.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pharmacy_product.Request.UserRequest;
import com.pharmacy_product.Response.ApiResponse;
import com.pharmacy_product.entities.UserEntity;
import com.pharmacy_product.exception.ApiException;
import com.pharmacy_product.repository.UserRepository;
import com.pharmacy_product.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final ModelMapper mapper;

	@Override
	public ApiResponse addUser(UserRequest userRequest) {
		// TODO Auto-generated method stub
		if(userRepository.existsByEmail(userRequest.getEmail())) {
			throw new ApiException("User already exist with this Email");
		}
		
		UserEntity user=mapper.map(userRequest, UserEntity.class);
		UserEntity userEntity=userRepository.save(user);
		
		return new ApiResponse("Admin Register Successfully!!!","Success");
	}

}
