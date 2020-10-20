package com.springboot.orderprocessing.dto;

import com.springboot.orderprocessing.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderStatusUpdate {
    private String orderId;
    private Order.Status status;
}
