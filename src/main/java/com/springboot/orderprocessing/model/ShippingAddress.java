package com.springboot.orderprocessing.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "shipping_address")
public class ShippingAddress {

    @Id
    private String id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String State;
    private int zipCode;
}
