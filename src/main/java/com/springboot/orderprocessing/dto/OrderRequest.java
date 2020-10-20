package com.springboot.orderprocessing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderRequest {

    private String customerId;
    @JsonProperty("orderDetails")
    private List<ItemDetails> itemDetails;
    private ShippingDetails shippingDetails;
    private List<PaymentDetails> paymentDetails;
}
