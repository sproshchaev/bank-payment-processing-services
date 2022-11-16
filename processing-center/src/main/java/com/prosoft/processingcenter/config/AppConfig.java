package com.prosoft.processingcenter.config;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableEurekaClient
@EnableJpaRepositories(basePackages = "com.prosoft.processingcenter.repository")
@EnableAspectJAutoProxy
public class AppConfig {

}
