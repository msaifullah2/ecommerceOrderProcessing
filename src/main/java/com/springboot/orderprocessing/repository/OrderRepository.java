package com.springboot.orderprocessing.repository;

import com.springboot.orderprocessing.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, String> {

}
