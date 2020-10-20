package com.springboot.orderprocessing.service;

import com.springboot.orderprocessing.model.ShippingAddress;
import com.springboot.orderprocessing.model.ShippingMethod;

import java.util.Optional;

public interface ShippingAddressService {

    public Optional<ShippingAddress> getShippingAddressById(String id);
}
