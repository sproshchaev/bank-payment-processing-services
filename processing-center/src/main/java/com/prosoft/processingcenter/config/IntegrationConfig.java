package com.prosoft.processingcenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;


@Configuration
@IntegrationComponentScan
public class IntegrationConfig {
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
                .handle("acquiringAuthorizationPhaseImpl", "checkTerminalParameters")
                .handle("issuerAuthorizationPhaseImpl", "checkCardParameters")
                .channel("outputChannel").get();
    }

}
