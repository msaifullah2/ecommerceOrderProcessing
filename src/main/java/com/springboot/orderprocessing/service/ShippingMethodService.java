package com.springboot.orderprocessing.service;


import com.springboot.orderprocessing.model.ShippingMethod;

import java.util.Optional;

public interface ShippingMethodService {

    public Optional<ShippingMethod> getShippingMethodById(String id);
}
