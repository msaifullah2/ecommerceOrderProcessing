package com.springboot.orderprocessing.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class OrderItemPK implements Serializable {

    @Column(name = "order_id")
    private String orderId;
    @Column(name = "item_id")
    private String itemId;
}
