### Microservice 6: "Конвертер курсов валют" currency-converter
1. Основной функционал: выполнение конверсионных операций из одной валюты в другую по курсам, публикуемым на сайте ЦБ РФ
2. Диаграмма развертывания: https://bit.ly/bpps-diagram
3. ERD-диаграмма отношений сущностей: https://bit.ly/bpps-erd-cc
4. Зависимости:
  - spring-cloud-starter-config (Config Client SPRING CLOUD CONFIG)
  - spring-cloud-starter-bootstrap (активация загрузки из bootstrap.properties)
  - spring-shell-starter (Spring Shell)
  - spring-boot-starter-web (Spring Web)
  - spring-boot-starter-thymeleaf (Thymeleaf)
  - spring-boot-starter-data-jpa (Spring Data JPA)
  - h2 (H2) примечание: консоль доступна в версии H2 2.1.214
  - grpc-netty-shaded, grpc-protobuf, grpc-stub, javax.annotation-api, os-maven-plugin
5. Конфигурация и настройки:
   - Настройки приложения: http://localhost:5000/currency-converter/default
   - Консоль H2:
     - URL: http://localhost:8083/h2-console
     - JDBC URL: jdbc:h2:mem:currency-converter

### Сервисы актуальных курсов валют
1. Fixer https://apilayer.com/marketplace/fixer-api?live_demo=show
2. Currency Data API https://apilayer.com/marketplace/currency_data-api?txn=free&live_demo=show

### Тестирование
1. Отключить Spring Shell в тестах в test\java\resources\application.properties: spring.shell.interactive.enabled=false, spring.main.allow-circular-references=true
2. В test\java\resources\ скопировать только data.sql (файл schema.sql в ресурсы тестов переносить нельзя иначе тесты будут тестировать не то, что находится в базе!)

### Статьи по теме
1. Обзор сервисов для получения актуальных курсов валют https://habr.com/ru/post/537784/
2. Как управлять вашими секретами с git-crypt https://habr.com/ru/company/nixys/blog/570306/
3. Как правильно работать с сѝкретами в проекте https://medium.com/maddevs-io/secrets-513d41eaaf43
4. gRPC — альтернатива REST API от Google https://www.youtube.com/watch?v=SMy4CaxizbA
5. Скалярные типы данных Protobuf https://learn.microsoft.com/ru-ru/dotnet/architecture/grpc-for-wcf-developers/protobuf-data-types
6. Пример Сервера на Spring Boot c аннотацией @GrpcService - Spring Boot gRPC Example (2022) https://www.techgeeknext.com/spring-boot/spring-boot-grpc-example
7. Spring Boot With H2 Database https://www.baeldung.com/spring-boot-h2-database
