package com.prosoft.currencyconverter.config;

import com.prosoft.currencyconverter.service.CurrencyExchangeRateServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Configuration
public class ConfigGrpc {
    @Autowired
    private ApplicationContext context;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Server startServerGrpc() {
        Server server = ServerBuilder
                .forPort(9090) // todo порт взять из настроек
                .addService(context.getBean(CurrencyExchangeRateServiceImpl.class))
                .build();
        try {
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return server;
    }

}
