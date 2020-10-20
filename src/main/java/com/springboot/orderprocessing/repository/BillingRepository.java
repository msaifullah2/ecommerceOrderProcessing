package com.springboot.orderprocessing.repository;

import com.springboot.orderprocessing.model.Billing;
import org.springframework.data.repository.CrudRepository;

public interface BillingRepository extends CrudRepository<Billing, String> {

}
