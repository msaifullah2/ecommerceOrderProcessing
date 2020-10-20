package com.springboot.orderprocessing.repository;

import com.springboot.orderprocessing.model.ShippingMethod;
import org.springframework.data.repository.CrudRepository;

public interface ShippingMethodRepository extends CrudRepository<ShippingMethod, String> {
}
