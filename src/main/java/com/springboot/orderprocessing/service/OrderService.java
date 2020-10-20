package com.springboot.orderprocessing.service;

import com.springboot.orderprocessing.dto.OrderRequest;
import com.springboot.orderprocessing.dto.OrderStatusUpdate;
import com.springboot.orderprocessing.exception.OrderInvalidException;
import com.springboot.orderprocessing.model.Order;

import java.util.List;

public interface OrderService {

    public Order createOrder(OrderRequest orderRequest) throws OrderInvalidException;

    public List<Order> createOrder(List<OrderRequest> orderRequests) throws OrderInvalidException;

    public Order getOrderById(String orderId) throws OrderInvalidException;

    public Order cancelOrder(String id) throws OrderInvalidException;

    public List<Order> updateStatus(List<OrderStatusUpdate> orderStatusUpdates);
}
