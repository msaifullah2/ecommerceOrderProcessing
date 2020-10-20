package com.springboot.orderprocessing.service;

import com.springboot.orderprocessing.model.Customer;

import java.util.Optional;

public interface CustomerService {

    public Optional<Customer> findByCustomerId(String customerId);
}
