package com.pharmacy_product.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pharmacy_product.Request.OrderItemRequest;
import com.pharmacy_product.Request.OrderRequest;
import com.pharmacy_product.Response.OrderResponse;
import com.pharmacy_product.entities.CustomerEntity;
import com.pharmacy_product.entities.OrderEntity;
import com.pharmacy_product.entities.OrderItemEntity;
import com.pharmacy_product.entities.ProductEntity;
import com.pharmacy_product.entities.enums.OrderStatus;
import com.pharmacy_product.exception.ApiException;
import com.pharmacy_product.repository.CustomerRepository;
import com.pharmacy_product.repository.OrderRepository;
import com.pharmacy_product.repository.ProductRepository;
import com.pharmacy_product.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	private final CustomerRepository customerRepository;

	private final ProductRepository productRepository;

	private final ModelMapper modelMapper;

	@Override
	public OrderResponse createOrder(OrderRequest orderRequest) {
		CustomerEntity customer = customerRepository.findById(orderRequest.getCustomerId())
				.orElseThrow(() -> new ApiException("Customer not found"));

		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setTotalAmount(orderRequest.getTotalAmount());
		orderEntity.setStatus(OrderStatus.PENDING);
		orderEntity.setOrderDate(LocalDateTime.now());
		orderEntity.setDeliveryAddress(orderRequest.getDeliveryAddress());
		orderEntity.setPaymentMethod(orderRequest.getPaymentMethod());
		orderEntity.setPrescriptionImage(orderRequest.getPrescriptionImage());
		orderEntity.setCustomer(customer);

		List<OrderItemEntity> orderItems = new ArrayList<>();
		for (OrderItemRequest itemRequest : orderRequest.getOrderItems()) {
			ProductEntity product = productRepository.findById(itemRequest.getProductId())
					.orElseThrow(() -> new ApiException("Product not found"));

			OrderItemEntity orderItem = new OrderItemEntity();
			orderItem.setQuantity(itemRequest.getQuantity());
			orderItem.setUnitPrice(product.getPrice());
			orderItem.setTotalPrice(product.getPrice() * itemRequest.getQuantity());
			orderItem.setOrder(orderEntity);
			orderItem.setProduct(product);
			orderItems.add(orderItem);
		}
		orderEntity.setOrderItems(orderItems);

		OrderEntity savedOrder = orderRepository.save(orderEntity);
		OrderResponse response = modelMapper.map(savedOrder, OrderResponse.class);
		response.setCustomerId(savedOrder.getCustomer().getId());
		return response;
	}

	@Override
	public List<OrderResponse> getAllOrders() {
		List<OrderEntity> orders = orderRepository.findAll();
		return orders.stream().map(order -> {
			OrderResponse response = modelMapper.map(order, OrderResponse.class);
			response.setCustomerId(order.getCustomer().getId());
			return response;
		}).toList();
	}

	
	@Override
	public List<OrderResponse> getOrdersByCustomerId(Long customerId) {

		CustomerEntity customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ApiException("Customer not found"));

		List<OrderEntity> orders = orderRepository.findByCustomerId(customer.getId());
		return orders.stream().map(order -> {
			OrderResponse response = modelMapper.map(order, OrderResponse.class);
			response.setCustomerId(order.getCustomer().getId());
			return response;
		}).toList();
	}
}