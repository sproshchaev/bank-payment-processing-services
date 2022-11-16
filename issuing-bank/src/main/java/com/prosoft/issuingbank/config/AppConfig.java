package com.prosoft.issuingbank.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.prosoft.issuingbank.repository")
public class AppConfig {

}
