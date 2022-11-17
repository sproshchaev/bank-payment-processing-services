package com.prosoft.issuingbank.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {
    @Bean
    public Queue newCardToProcessingCenter() {
        return new Queue("newCardToProcessingCenter", false);
    }

    @Bean
    public Queue newCardToIssuingBank() {
        return new Queue("newCardToIssuingBank", false);
    }

    @Bean
    public Queue transactionQueue() {
        return new Queue("transactionQueue", false);
    }
}
