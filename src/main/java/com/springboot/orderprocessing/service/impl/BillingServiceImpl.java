package com.springboot.orderprocessing.service.impl;

import com.springboot.orderprocessing.model.Billing;
import com.springboot.orderprocessing.model.Item;
import com.springboot.orderprocessing.repository.BillingRepository;
import com.springboot.orderprocessing.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingServiceImpl implements BillingService {

    private final BillingRepository billingRepository;

    @Autowired
    public BillingServiceImpl(BillingRepository billingRepository) {
        this.billingRepository = billingRepository;
    }

    @Override
    public List<Billing> findAllByIds(List<String> ids) {
        return (List<Billing>) billingRepository.findAllById(ids);
    }
}
