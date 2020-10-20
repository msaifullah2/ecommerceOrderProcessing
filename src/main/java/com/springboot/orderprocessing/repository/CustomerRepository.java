package com.springboot.orderprocessing.repository;

import com.springboot.orderprocessing.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String> {

}
