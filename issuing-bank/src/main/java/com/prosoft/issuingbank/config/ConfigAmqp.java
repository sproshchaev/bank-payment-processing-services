package com.prosoft.issuingbank.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigAmqp {
    @Bean
    public Queue newCardQueue() {
        return new Queue("newCardQueue", false);
    }

    @Bean
    public Queue transactionQueue() {
        return new Queue("transactionQueue", false);
    }
}
