package com.springboot.orderprocessing.service.impl;

import com.springboot.orderprocessing.model.ShippingAddress;
import com.springboot.orderprocessing.repository.ShippingAddressRepository;
import com.springboot.orderprocessing.service.ShippingAddressService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {

    private final ShippingAddressRepository shippingAddressRepository;

    public ShippingAddressServiceImpl(ShippingAddressRepository shippingAddressRepository) {
        this.shippingAddressRepository = shippingAddressRepository;
    }

    @Override
    public Optional<ShippingAddress> getShippingAddressById(String id) {
        return shippingAddressRepository.findById(id);
    }
}
