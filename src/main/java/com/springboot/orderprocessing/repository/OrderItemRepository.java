package com.springboot.orderprocessing.repository;

import com.springboot.orderprocessing.model.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, String> {
}
