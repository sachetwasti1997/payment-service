package com.sachet.payment_service.controller;

import com.sachet.payment_service.exceptions.PaymentCreationException;
import com.sachet.payment_service.model.Payment;
import com.sachet.payment_service.model.PaymentIntentDto;
import com.sachet.payment_service.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentsController {

    private final PaymentService paymentService;

    public PaymentsController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/make-payment")
    public PaymentIntentDto createPaymentIntent(@RequestBody Payment payment) throws PaymentCreationException {
        return paymentService.createPaymentIntent(payment);
    }
}
