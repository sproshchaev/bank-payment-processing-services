package com.prosoft.processingcenter.config;

import org.springframework.amqp.core.Queue;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;

// todo @EnableEurekaClient + в pom.xml добавить spring-cloud-starter-netflix-eureka-client + в application.properties добавить
// todo @EnableFeignClient(basePackages="...") + в pom.xml добавить

@EnableEurekaClient
@EnableIntegration
@IntegrationComponentScan
@EnableJpaRepositories(basePackages = "com.prosoft.processingcenter.repository")
@Configuration
public class ConfigApp {
    @Bean
    public QueueChannel inputChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel outputChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public IntegrationFlow authorizationFlow() {
        return IntegrationFlows.from("inputChannel")
                .handle("acquiringAuthorizationPhase", "doServiceA")
                .handle("issuerAuthorizationPhase", "doServiceB")
                .channel("outputChannel").get();
    }

    @Bean
    public Queue newCardQueue() {
        return new Queue("newCardQueue", false);
    }

    @Bean
    public Queue transactionQueue() {
        return new Queue("transactionQueue", false);
    }

}
