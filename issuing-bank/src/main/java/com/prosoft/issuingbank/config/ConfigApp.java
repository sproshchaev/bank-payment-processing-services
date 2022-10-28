package com.prosoft.issuingbank.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
@Configuration
public class ConfigApp {

    @Bean
    public Queue newCardQueue() {
        return new Queue("newCardQueue", false);
    }

    @Bean
    public Queue transactionQueue() {
        return new Queue("transactionQueue", false);
    }

}
