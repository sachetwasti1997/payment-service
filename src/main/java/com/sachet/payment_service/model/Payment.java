package com.sachet.payment_service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment {

    private Long orderId;
    private double price;
    private String currency;

}
