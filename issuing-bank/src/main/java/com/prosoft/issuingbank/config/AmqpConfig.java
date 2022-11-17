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

    @Bean // todo rename transactionToProcessingCenter
    public Queue transactionQueue() {
        return new Queue("transactionQueue", false);
    }

    @Bean // todo rename transactionToIssuingBank
    public Queue transactionQueue2() {
        return new Queue("transactionQueue2", false);
    }

}
