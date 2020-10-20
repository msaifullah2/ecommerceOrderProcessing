package com.springboot.orderprocessing.dto;

import com.springboot.orderprocessing.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class OrderResponse {
    private Order data;
    private String message;
    private HttpStatus status;
    private String confirmNumber;
}
