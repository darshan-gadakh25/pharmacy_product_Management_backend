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
import com.pharmacy_product.Request.OrderPaymentRequest;
import com.pharmacy_product.Response.OrderResponse;
import com.pharmacy_product.Response.PaymentResponse;
import com.pharmacy_product.entities.BillingEntity;
import com.pharmacy_product.entities.CustomerEntity;
import com.pharmacy_product.entities.OrderEntity;
import com.pharmacy_product.entities.OrderItemEntity;
import com.pharmacy_product.entities.ProductEntity;
import com.pharmacy_product.entities.enums.OrderStatus;
import com.pharmacy_product.entities.enums.PaymentMethod;
import com.pharmacy_product.exception.ApiException;
import com.pharmacy_product.repository.BillingRepository;
import com.pharmacy_product.repository.CustomerRepository;
import com.pharmacy_product.repository.OrderRepository;
import com.pharmacy_product.repository.ProductRepository;
import com.pharmacy_product.service.OrderService;
import com.pharmacy_product.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	private final CustomerRepository customerRepository;

	private final ProductRepository productRepository;

	private final BillingRepository billingRepository;

	private final PaymentService paymentService;

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

	@Override
	public void confirmOrder(Long orderId) {
		OrderEntity order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ApiException("Order not found"));
		
		if (order.getStatus() != OrderStatus.PENDING) {
			throw new ApiException("Only pending orders can be confirmed");
		}
		
		order.setStatus(OrderStatus.CONFIRMED);
		orderRepository.save(order);
	}

	@Override
	public void cancelOrder(Long orderId) {
		OrderEntity order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ApiException("Order not found"));
		
		if (order.getStatus() == OrderStatus.DELIVERED || order.getStatus() == OrderStatus.CANCELLED) {
			throw new ApiException("Cannot cancel delivered or already cancelled orders");
		}
		
		order.setStatus(OrderStatus.CANCELLED);
		orderRepository.save(order);
	}

	@Override
	public OrderResponse createOrderWithPayment(OrderPaymentRequest orderRequest) {
		CustomerEntity customer = customerRepository.findById(orderRequest.getCustomerId())
				.orElseThrow(() -> new ApiException("Customer not found"));

		// If credit card payment, validate and process payment first
		if (orderRequest.getPaymentMethod() == PaymentMethod.CREDIT_CARD) {
			if (orderRequest.getCreditCard() == null) {
				throw new ApiException("Credit card details required for credit card payment");
			}

			// Validate credit card
			if (!paymentService.validateCreditCard(orderRequest.getCreditCard())) {
				throw new ApiException("Invalid credit card details");
			}

			// Process payment
			PaymentResponse paymentResponse = paymentService.processPayment(
					orderRequest.getCreditCard(), orderRequest.getTotalAmount());

			if (!"SUCCESS".equals(paymentResponse.getStatus())) {
				throw new ApiException("Payment failed: " + paymentResponse.getMessage());
			}

			// Create order with CONFIRMED status for successful payment
			OrderEntity orderEntity = createOrderEntity(orderRequest, customer, OrderStatus.CONFIRMED);

			// Create billing record
			BillingEntity billing = new BillingEntity();
			billing.setCardNumber(maskCardNumber(orderRequest.getCreditCard().getCardNumber()));
			billing.setCardHolderName(orderRequest.getCreditCard().getCardHolderName());
			billing.setExpiryMonth(orderRequest.getCreditCard().getExpiryMonth());
			billing.setExpiryYear(orderRequest.getCreditCard().getExpiryYear());
			billing.setCvv("***"); // Never store actual CVV
			billing.setBillingAddress(orderRequest.getCreditCard().getBillingAddress());
			billing.setPaymentStatus(paymentResponse.getStatus());
			billing.setTransactionId(paymentResponse.getTransactionId());
			billing.setOrder(orderEntity);

			orderEntity.setBilling(billing);
			OrderEntity savedOrder = orderRepository.save(orderEntity);

			OrderResponse response = modelMapper.map(savedOrder, OrderResponse.class);
			response.setCustomerId(savedOrder.getCustomer().getId());
			return response;

		} else {
			// Cash on delivery - create order with PENDING status
			OrderEntity orderEntity = createOrderEntity(orderRequest, customer, OrderStatus.PENDING);
			OrderEntity savedOrder = orderRepository.save(orderEntity);

			OrderResponse response = modelMapper.map(savedOrder, OrderResponse.class);
			response.setCustomerId(savedOrder.getCustomer().getId());
			return response;
		}
	}

	private OrderEntity createOrderEntity(OrderPaymentRequest orderRequest, CustomerEntity customer, OrderStatus status) {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setTotalAmount(orderRequest.getTotalAmount());
		orderEntity.setStatus(status);
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
		return orderEntity;
	}

	private String maskCardNumber(String cardNumber) {
		if (cardNumber.length() < 4) return cardNumber;
		return "****-****-****-" + cardNumber.substring(cardNumber.length() - 4);
	}
}