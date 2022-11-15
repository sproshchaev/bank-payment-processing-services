package com.prosoft.currencyconverter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.prosoft.currencyconverter.repository")
public class ConfigApp {
}
