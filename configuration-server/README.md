### Microservice 1: "Конфигурационный сервер" configuration-server
1. Основной функционал: управление файлами application.properties микросервисов на Github https://bit.ly/bpps-config
2. Зависимости:
  - Config Server (SPRING CLOUD CONFIG): spring-cloud-config-server
3. Конфигурация и настройки:
  - Main: @EnableConfigServer
  - bootstrap.properties

### Secret
  - application.properties: encrypt.key, spring.security.user.name, spring.security.user.password, spring.cloud.config.server.encrypt.enabled=false(!)  
  - encrypt Postman: POST, localhost:5000/encrypt, Authorization=Basic Auth, Body raw = "value" -> "cryptValue" 
  - decrypt Postman: POST, localhost:5000/decrypt, Authorization=Basic Auth, Body raw = "cryptValue" -> "value" 
  - Application: (GitHub) application.properties -> {cipher}"cryptValue"

### Статьи по теме

