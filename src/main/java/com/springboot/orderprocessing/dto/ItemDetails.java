package com.springboot.orderprocessing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDetails {

    private String itemId;
    private int quantity;
}
