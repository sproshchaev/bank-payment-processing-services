### Microservice 4: "Торговая точка" sales-point
1. Основной функционал: формирование заказа клиента со списком покупок, авторизация платежа за покупки по банковским
   картам VISA, MasterCard, Мир в процессинговом центре, возврат средств на карту клиента при отказе от покупки (возврате
   товара)
2. Диаграмма развертывания: https://bit.ly/bpps-diagram
3. ERD-диаграмма отношений сущностей: https://bit.ly/bpps-erd-sp
4. Зависимости:
  - spring-cloud-starter-config (Config Client SPRING CLOUD CONFIG)
  - spring-cloud-starter-bootstrap (активация загрузки из bootstrap.properties)
  - spring-shell-starter (Spring Shell)
  - spring-boot-starter-web (Spring Web)
  - spring-boot-starter-thymeleaf (Thymeleaf)
  - spring-boot-starter-data-jpa (Spring Data JPA)
  - h2 (H2) примечание: консоль доступна в версии H2 2.1.214
  - spring-boot-starter-security (Spring Security)
  - spring-cloud-starter-netflix-eureka-client (Eureka Discovery Client SPRING CLOUD DISCOVERY)
  - spring-boot-starter-actuator
5. Конфигурация и настройки:
  - Actuator: http://localhost:8082/actuator
  - Настройки приложения: http://localhost:5000/sales-point/default
  - Консоль H2:
    - URL: http://localhost:8082/h2-console
    - JDBC URL: jdbc:h2:mem:sales-point

### Shell commands
  - Make authorization: ma --tid <tid> --date <date> --card <card> --expdate <expdate> --sum <sum> --curr <curr>

### Тестирование
1. Отключить Spring Shell в тестах в test\java\resources\application.properties: spring.shell.interactive.enabled=false, spring.main.allow-circular-references=true
2. В test\java\resources\ скопировать только data.sql (файл schema.sql в ресурсы тестов переносить нельзя иначе тесты будут тестировать не то, что находится в базе!)
 
### Статьи по теме
1. Краткий обзор Spring Security, создание проекта и подключение зависимостей https://highload.today/spring-security/
2. Создание REST-клиента с помощью Spring Cloud OpenFeign и Netflix Ribbon https://bit.ly/3FhBZ2z
3. Introduction to Spring Cloud OpenFeign https://www.baeldung.com/spring-cloud-openfeign
4. Наглядный пример различия DTO, POJO и Value Object - Stack Overflow на русском https://bit.ly/3pFYvtg
5. Spring Boot With H2 Database https://www.baeldung.com/spring-boot-h2-database
6. @RequestMapping annotation not allowed on @FeignClient interfaces https://bit.ly/3fx3Wcs
