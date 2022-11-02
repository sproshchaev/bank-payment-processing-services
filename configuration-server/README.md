### Microservice 1: "Конфигурационный сервер" configuration-server
1. Основной функционал: управление файлами application.properties микросервисов на Github https://bit.ly/bpps-config
2. Зависимости:
  - Config Server (SPRING CLOUD CONFIG): spring-cloud-config-server
  - jasypt-spring-boot-starter, jasypt-maven-plugin
3. Конфигурация и настройки:
  - Настройки приложения: http://localhost:5000/configuration-server/default
  - Main: @EnableConfigServer
  - bootstrap.properties

### Secure configuration properties
  - application.properties: encrypt.key, spring.security.user.name, spring.security.user.password, spring.cloud.config.server.encrypt.enabled=false(!)  
  - encrypt Postman: POST, localhost:5000/encrypt, Authorization=Basic Auth, Body raw = "value" -> "cryptValue" 
  - decrypt Postman: POST, localhost:5000/decrypt, Authorization=Basic Auth, Body raw = "cryptValue" -> "value" 
  - Application: (GitHub) application.properties -> {cipher}"cryptValue"

### Jasypt
  - application.properties: spring.security.user.name, spring.security.user.password
  - шифрование параметра командой: mvn jasypt:encrypt -Djasypt.encryptor.password=<...>
  - см. encrypt.key (value=psw)

### Статьи по теме
1. Вебинар 37 "Zuul, Hystrix Circuit Breaker, Sleuth, Zipkin, Hystrix Dashboard, Secure Configuration Properties" - 01:25:03 Secure configuration properties
2. Конфигурация Spring Boot с Jasypt https://www.baeldung.com/spring-boot-jasypt
3. Spring Cloud Config Server - Encryption and Decryption https://www.springcloud.io/post/2022-03/spring-cloud-config-server-encryption-and-decryption/#gsc.tab=0