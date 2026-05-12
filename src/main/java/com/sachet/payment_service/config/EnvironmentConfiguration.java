package com.sachet.payment_service.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sachet.payment_service.config.model.DatabaseConfiguration;
import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "payment.config")
public class EnvironmentConfiguration {
    private DatabaseConfiguration databaseConfiguration;
    private Map<String, String> topics;
}

@Slf4j
@Configuration
class ServiceConfig{
    @Value("${payment.config.stripe_key}")
    private String stripeKey;

    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @PostConstruct
    public void initKey() {
        log.info("Key Init...");
        Stripe.apiKey = stripeKey;
    }
}
