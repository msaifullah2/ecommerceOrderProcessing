package com.springboot.orderprocessing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingDetails {

    private String shippingMethodId;
    private String shippingAddressId;
}
