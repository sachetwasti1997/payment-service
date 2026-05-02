package com.sachet.payment_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sachet.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaService {
    Logger LOGGER = LoggerFactory.getLogger(KafkaService.class);

    private final OrderService orderService;

    public KafkaService( OrderService orderService) {
        this.orderService = orderService;
    }

//    @KafkaListener(topics = "user-add-product", groupId = "${spring.kafka.consumer.group-id}")
//    public void consumer(String data) {
//        LOGGER.info("The message: {}", data);
//    }

//    @KafkaListener(topics = "user-product-review", groupId = "${spring.kafka.consumer.group-id}")
//    public void consumeReview(String data) {
//        try {
//            LOGGER.info("The message received is: {}", data);
//            productService.saveProductReview(data);
//        }catch (JsonProcessingException ex) {
//            LOGGER.error("Caught an exception while reading event: {}", ex.getMessage());
//        }
//    }

    @KafkaListener(topics = "order-created", groupId = "${spring.kafka.consumer.group-id}")
    public void listenOrderCreated(OrderDto data) throws JsonProcessingException {
        LOGGER.info("Received order created event: {}", data);
        orderService.consumeOrderEvent(data);
    }

    @KafkaListener(topics = "order-cancelled", groupId = "${spring.kafka.consumer.group-id}")
    public void listenOrderCancelled(OrderDto data) throws JsonProcessingException {
        LOGGER.info("Received order cancelled event: {}", data);
        orderService.consumeOrderEvent(data);
    }

    @KafkaListener(topics = "order-expired", groupId = "${spring.kafka.consumer.group-id}")
    public void listenOrderExpired(OrderDto data) throws JsonProcessingException {
        LOGGER.info("Received order expired event: {}", data);
        orderService.consumeOrderCancelledEvent(data);
    }
}
