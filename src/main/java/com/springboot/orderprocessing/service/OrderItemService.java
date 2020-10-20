package com.springboot.orderprocessing.service;

import com.springboot.orderprocessing.model.OrderItem;

import java.util.List;

public interface OrderItemService {

    public List<OrderItem> saveAll(List<OrderItem> orderItem);
}
