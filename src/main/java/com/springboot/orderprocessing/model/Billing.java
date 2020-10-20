package com.springboot.orderprocessing.model;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "billing")
public class Billing {

    @Id
    private String id;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "customer_fk"))
    private Customer customer;
    private String cardNumber;
    private CardType cardType;
    private String expiryDate;
    private String cvv;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private int zip;

    public enum CardType {
        DEBIT_CARD,
        CREDIT_CARD
    }
}
