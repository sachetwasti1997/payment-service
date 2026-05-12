package com.sachet.payment_service.exceptions;

import lombok.Getter;

@Getter
public class PaymentCreationException extends Throwable {
    public PaymentCreationException(String code, String message) {
        super();
    }
}
