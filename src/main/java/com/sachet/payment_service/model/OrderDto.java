package com.sachet.payment_service.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderDto {

    private long id;
    private String userId;
    private String status;
    private Date expiresAt;
    private Long productId;
    private double price;
    private int count;
    private String sellerEmail;

}
