package com.springboot.orderprocessing.service.impl;

import com.springboot.orderprocessing.model.OrderItem;
import com.springboot.orderprocessing.repository.OrderItemRepository;
import com.springboot.orderprocessing.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public List<OrderItem> saveAll(List<OrderItem> orderItems) {
        return (List<OrderItem>) orderItemRepository.saveAll(orderItems);
    }
}
