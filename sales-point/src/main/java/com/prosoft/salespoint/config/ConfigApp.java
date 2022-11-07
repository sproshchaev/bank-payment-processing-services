package com.prosoft.salespoint.config;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;


@Configuration
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.prosoft.salespoint.feign")
@EnableJpaRepositories
public class ConfigApp {

  @Bean
  // @LoadBalanced - если используется feign (@EnableFeignClients), то аннотацию @LoadBalanced не включаем, так как: feign использует ribbon и eureka
  public RestTemplate getRestTemplate() {
      return new RestTemplate();
  }

}
