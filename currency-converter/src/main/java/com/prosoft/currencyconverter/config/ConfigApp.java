package com.prosoft.currencyconverter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@EnableJpaRepositories
@Configuration
public class ConfigApp {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
