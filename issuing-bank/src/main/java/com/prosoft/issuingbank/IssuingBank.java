package com.prosoft.issuingbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class IssuingBank {

	public static void main(String[] args) {
		SpringApplication.run(IssuingBank.class, args);
	}

}
