package com.springboot.orderprocessing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OrderInvalidException extends Exception{
    private static final long serialVersionUID = 1L;
    public OrderInvalidException(String message) {
        super(message);
    }
}
