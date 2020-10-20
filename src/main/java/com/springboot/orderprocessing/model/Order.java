package com.springboot.orderprocessing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "order")
public class Order {
    @Id
    private String id;
    private Status status;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "customer_fk"))
    private Customer customer;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "shipping_fk"))
    private ShippingMethod shipping;
    private Double subTotal;
    private Double tax;
    private Double total;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String confirmNumber;


    public enum Status {
        PROCESSING,
        PROCESSED,
        CANCELLED
    }
}
