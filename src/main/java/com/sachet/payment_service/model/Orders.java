package com.sachet.payment_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@Entity
@ToString
@Table(name = "orders")
public class Orders {
    @Id
    private long id;
    private String userId;
    private String status;
    private Date expiresAt;
    private Long productId;
    private double price;
    private int count;
    private String sellerEmail;
}
