package com.sachet.payment_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sachet.OrderDto;
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

    public void consumeOrderEvent(OrderDto orderDto) throws JsonProcessingException {
        Orders orders = new Orders();
        orders.setId(orderDto.getOrderId());
        orders.setCount(orderDto.getCount());
        orders.setPrice(orderDto.getPrice());
        orders.setProductId(orderDto.getProductId());
        orders.setStatus(orderDto.getStatus().toString());
        orders.setUserId(orderDto.getBuyerEmail().toString());
        orders.setSellerEmail(orderDto.getSellerEmail().toString());
        LOGGER.info("Saving the Order: {}", orders);
        orderRepository.save(orders);
    }

    public void consumeOrderCancelledEvent(OrderDto orderDto) throws JsonProcessingException {
        Orders orders = new Orders();
        orders.setId(orderDto.getOrderId());
        orders.setPrice(orderDto.getPrice());
        orders.setCount(orderDto.getCount());
        orders.setStatus(orderDto.getStatus().toString());
        orders.setUserId(orderDto.getBuyerEmail().toString());
        orders.setProductId(orderDto.getProductId());
        orders.setSellerEmail(orderDto.getSellerEmail().toString());
        LOGGER.info("Saving the order cancelled: {}", orders);
        orderRepository.save(orders);
    }
}
