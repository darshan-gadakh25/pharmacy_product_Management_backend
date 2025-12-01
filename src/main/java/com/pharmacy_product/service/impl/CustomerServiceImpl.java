package com.pharmacy_product.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pharmacy_product.Request.CustomerRequest;
import com.pharmacy_product.Response.ApiResponse;
import com.pharmacy_product.entities.CustomerEntity;
import com.pharmacy_product.entities.UserEntity;
import com.pharmacy_product.entities.enums.UserRole;
import com.pharmacy_product.exception.ApiException;
import com.pharmacy_product.repository.CustomerRepository;
import com.pharmacy_product.repository.UserRepository;
import com.pharmacy_product.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final UserRepository userRepository;
	private final ModelMapper mapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public ApiResponse addCustomer(CustomerRequest customerRequest) {

		if (userRepository.existsByEmail(customerRequest.getUserDetails().getEmail())) {
			throw new ApiException("Customer already exist with this Email");
		}

		CustomerEntity customer = mapper.map(customerRequest, CustomerEntity.class);
		customer.getUserDetails().setRole(UserRole.CUSTOMER);
		customer.getUserDetails().setPassword(passwordEncoder.encode(customer.getUserDetails().getPassword()));

		customerRepository.save(customer);

		return new ApiResponse("Customer Register Successfully!!!", "Success");
	}
}