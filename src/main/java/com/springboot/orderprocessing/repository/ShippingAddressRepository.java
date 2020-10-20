package com.springboot.orderprocessing.repository;

import com.springboot.orderprocessing.model.ShippingAddress;
import org.springframework.data.repository.CrudRepository;

public interface ShippingAddressRepository extends CrudRepository<ShippingAddress, String> {
}
