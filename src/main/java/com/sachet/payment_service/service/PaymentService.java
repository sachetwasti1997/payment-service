package com.sachet.payment_service.service;

import com.sachet.payment_service.exceptions.PaymentCreationException;
import com.sachet.payment_service.model.Orders;
import com.sachet.payment_service.model.Payment;
import com.sachet.payment_service.model.PaymentIntentDto;
import com.sachet.payment_service.repo.OrderRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PaymentService {

    private final OrderRepository orderRepository;

    public PaymentService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public PaymentIntentDto createPaymentIntent(Payment payment) throws PaymentCreationException {
        try {
            Optional<Orders> orders = orderRepository.findById(payment.getOrderId());
            if (orders.isEmpty()) {
                log.warn("The order doesnt exist so cannot make payment");
                return null;
            }
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(orders.get().getCount() * (long) orders.get().getPrice())
                    .setCurrency(payment.getCurrency())
                    .setPaymentMethod(PaymentMethod.)
                    .build();
            PaymentIntent paymentIntent = PaymentIntent.create(params);
            PaymentIntentDto dto = new PaymentIntentDto();
            dto.setClientSecret(paymentIntent.getClientSecret());

            return dto;
        } catch (StripeException e) {
            log.error("Error while doing the payment");
            throw new PaymentCreationException(e.getCode(), e.getMessage());
        }
    }

}
