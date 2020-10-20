package com.springboot.orderprocessing.service.impl;

import com.springboot.orderprocessing.model.ShippingMethod;
import com.springboot.orderprocessing.repository.ShippingMethodRepository;
import com.springboot.orderprocessing.service.ShippingMethodService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ShippingMethodServiceImpl implements ShippingMethodService {

    private final ShippingMethodRepository shippingMethodRepository;

    public ShippingMethodServiceImpl(ShippingMethodRepository shippingMethodRepository) {
        this.shippingMethodRepository = shippingMethodRepository;
    }

    @Override
    public Optional<ShippingMethod> getShippingMethodById(String id) {
        return shippingMethodRepository.findById(id);
    }
}
