package com.springboot.orderprocessing.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "item")
public class Item {
    @Id
    @Column(name = "item_id")
    private String id;
    private String name;
    private String manufacturer;
    private Double price;
    @CreationTimestamp
    private LocalDateTime createdDate = LocalDateTime.now();
    @UpdateTimestamp
    private LocalDateTime modifiedDate = LocalDateTime.now();
}
