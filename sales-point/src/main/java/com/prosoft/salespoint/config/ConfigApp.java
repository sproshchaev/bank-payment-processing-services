package com.prosoft.salespoint.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;


// todo @EnableEurekaClient + в pom.xml добавить spring-cloud-starter-netflix-eureka-client + в application.properties добавить
// todo @EnableFeignClient(basePackages="...") + в pom.xml добавить
@EnableJpaRepositories
@Configuration
public class ConfigApp {

  @Bean
  public RestTemplate getRestTemplate() {
      return new RestTemplate();
  }

}
