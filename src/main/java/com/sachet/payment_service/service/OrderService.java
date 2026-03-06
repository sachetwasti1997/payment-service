package com.sachet.payment_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sachet.payment_service.model.OrderDto;
import com.sachet.payment_service.model.Orders;
import com.sachet.payment_service.repo.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;

    public OrderService(OrderRepository orderRepository, ObjectMapper objectMapper) {
        this.orderRepository = orderRepository;
        this.objectMapper = objectMapper;
    }

    public void consumeOrderCreatedEvent(String orderDto) throws JsonProcessingException {
        Orders orders = objectMapper.readValue(orderDto, Orders.class);
        LOGGER.info("Saving the Order created: {}", orders);
        orderRepository.save(orders);
    }

    public void consumeOrderCancelledEvent(String productDto) throws JsonProcessingException {
        Orders product = objectMapper.readValue(productDto, Orders.class);
        LOGGER.info("Saving the order cancelled: {}", product);
        orderRepository.save(product);
    }
}
