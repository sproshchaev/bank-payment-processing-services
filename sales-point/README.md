### Microservice 4: "Торговая точка" sales-point
1. Основной функционал: формирование заказа клиента со списком покупок, авторизация платежа за покупки по банковским
   картам VISA, MasterCard, Мир в процессинговом центре, возврат средств на карту клиента при отказе от покупки (возврате
   товара)
2. Зависимости:
  - spring-cloud-starter-config (Config Client SPRING CLOUD CONFIG)
  - spring-cloud-starter-bootstrap (активация загрузки из bootstrap.properties)
  - spring-shell-starter (Spring Shell)
  - spring-boot-starter-web (Spring Web)
  - spring-boot-starter-thymeleaf (Thymeleaf)
  - spring-boot-starter-data-jpa (Spring Data JPA)
  - h2 (H2)
  - spring-boot-starter-security (Spring Security)
  - spring-cloud-starter-netflix-eureka-client (Eureka Discovery Client SPRING CLOUD DISCOVERY)
3. Конфигурация и настройки:
   - Консоль H2:
     - URL: http://localhost:8082/h2-console
     - JDBC URL: jdbc:h2:mem:sales-point

### Таблицы sales-point
  UML https://dbdiagram.io/d/635780c2fa2755667d671cce

### Shell commands
  - Make authorization: ma --tid <tid> --date <date> --card <card> --expdate <expdate> --sum <sum> --curr <curr>
  
### Статьи по теме
1. Создание REST-клиента с помощью Spring Cloud OpenFeign и Netflix Ribbon https://bit.ly/3FhBZ2z
2. Introduction to Spring Cloud OpenFeign https://www.baeldung.com/spring-cloud-openfeign

