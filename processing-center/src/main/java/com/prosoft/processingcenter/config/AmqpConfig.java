package com.prosoft.processingcenter.config;

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
    public Queue transactionToProcessingCenter() {
        return new Queue("transactionToProcessingCenter", false);
    }

    @Bean
    public Queue transactionToIssuingBank() {
        return new Queue("transactionToIssuingBank", false);
    }

}
