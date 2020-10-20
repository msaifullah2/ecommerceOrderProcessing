package com.springboot.orderprocessing.controller;

import com.springboot.orderprocessing.dto.OrderRequest;
import com.springboot.orderprocessing.dto.OrderStatusUpdate;
import com.springboot.orderprocessing.exception.OrderInvalidException;
import com.springboot.orderprocessing.model.Order;
import com.springboot.orderprocessing.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order/create")
    @ApiOperation(value = "Creates an order from the cart", notes = "Provide an order object to create an order ", response = Order.class)
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request) throws OrderInvalidException {
        return new ResponseEntity<>(orderService.createOrder(request), HttpStatus.CREATED);
    }

    @PostMapping("/order/create/bulk")
    @ApiOperation(value = "Creates bulk orders at a time", notes = "Provide list of order object to create an bulk order", response = Order.class)
    public ResponseEntity<List<Order>> createOrders(@RequestBody List<OrderRequest> requests) throws OrderInvalidException {
        return new ResponseEntity<>(orderService.createOrder(requests), HttpStatus.OK);
    }

    @PutMapping("/order/updateStatus/bulk")
    @ApiOperation(value = "Updates bulk order statuses at a time", notes = "Provide list of order object to update bulk order statuses", response = Order.class)
    public ResponseEntity<List<Order>> updateOrders(@RequestBody List<OrderStatusUpdate> requests) throws OrderInvalidException {
        List<Order> orders = orderService.updateStatus(requests);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    @ApiOperation(value = "Get order based on the order id", notes = "Provide an order id to get the order object", response = Order.class)
    public ResponseEntity<Order> getOrderById(@PathVariable(value = "id") String orderId) throws OrderInvalidException {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok().body(order);
    }

    @PutMapping("/order/cancel/{id}")
    @ApiOperation(value = "Cancels order based on the order id", notes = "Provide an order id to cancel an order", response = Order.class)
    public ResponseEntity<Order> cancelOrder(@PathVariable(value = "id") String orderId) throws OrderInvalidException {
        Order cancelledOrder = orderService.cancelOrder(orderId);
        return ResponseEntity.ok().body(cancelledOrder);
    }
}
