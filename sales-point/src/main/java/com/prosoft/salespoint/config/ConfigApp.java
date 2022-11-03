package com.prosoft.salespoint.config;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;


@EnableEurekaClient
@EnableJpaRepositories
@EnableFeignClients(basePackages = "com.prosoft.salespoint.feign")
@Configuration
public class ConfigApp {

  @Bean
  public RestTemplate getRestTemplate() {
      return new RestTemplate();
  }

}
