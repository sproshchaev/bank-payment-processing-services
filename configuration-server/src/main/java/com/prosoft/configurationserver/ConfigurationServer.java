package com.prosoft.configurationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Приложение "Сервер конфигурации"
 *
 * @author Sergey Proshchaev
 * @version 1.0
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigurationServer {

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationServer.class, args);
    }

}
