package com.springboot.orderprocessing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentDetails {

    private String paymentId;
    private Double charge;
}
