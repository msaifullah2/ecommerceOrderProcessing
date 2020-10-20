package com.springboot.orderprocessing.service;

import com.springboot.orderprocessing.model.Billing;
import com.springboot.orderprocessing.model.Item;

import java.util.List;

public interface BillingService {

    public List<Billing> findAllByIds(List<String> ids);
}
