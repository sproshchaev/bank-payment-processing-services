package com.prosoft.processingcenter.config;

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


@EnableIntegration
@IntegrationComponentScan
@EnableJpaRepositories
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
                .handle("serviceA", "doServiceA")
                .handle("serviceB", "doServiceB")
                .channel("outputChannel").get();
    }

}
