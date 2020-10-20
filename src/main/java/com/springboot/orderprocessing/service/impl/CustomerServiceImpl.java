package com.springboot.orderprocessing.service.impl;

import com.springboot.orderprocessing.model.Customer;
import com.springboot.orderprocessing.repository.CustomerRepository;
import com.springboot.orderprocessing.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Customer> findByCustomerId(String customerId) {
        return customerRepository.findById(customerId);
    }
}
