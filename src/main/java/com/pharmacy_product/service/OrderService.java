package com.pharmacy_product.service;

import java.util.List;

import com.pharmacy_product.Request.OrderRequest;
import com.pharmacy_product.Request.OrderPaymentRequest;
import com.pharmacy_product.Response.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);
    OrderResponse createOrderWithPayment(OrderPaymentRequest orderRequest);
    List<OrderResponse> getAllOrders();
    List<OrderResponse> getOrdersByCustomerId(Long customerId);
    void confirmOrder(Long orderId);
    void cancelOrder(Long orderId);
}