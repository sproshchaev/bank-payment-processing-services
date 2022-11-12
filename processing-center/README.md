### Microservice 2: "Процессинговый центр" processing-center
1. Основной функционал: ведение актуальной базы банковских карт VISA, MasterCard, Мир с платежными лимитами, доступными
   для совершения операций, авторизация покупок по банковским картам VISA, MasterCard, Мир в торговых точках (один
   процессинг на несколько торговых точек). Обмен информацией об эмиссии и транзакциях с банками-эмитентами (один процессинг
   на несколько банков-эмитентов)
2. Диаграмма развертывания: https://bit.ly/bpps-diagram
3. ERD-диаграмма отношений сущностей: https://bit.ly/bpps-erd-pc
4. Зависимости:
  - spring-cloud-starter-config (Config Client SPRING CLOUD CONFIG)
  - spring-cloud-starter-bootstrap (активация загрузки из bootstrap.properties)
  - spring-shell-starter (Spring Shell)
  - spring-boot-starter-integration (Spring Integration)
  - spring-boot-starter-web (Spring Web) 
  - spring-boot-starter-data-jpa (Spring Data JPA)
  - h2 (H2) примечание: консоль доступна в версии H2 2.1.214
  - grpc-netty-shaded, grpc-protobuf, grpc-stub, javax.annotation-api, os-maven-plugin
5. Конфигурация и настройки:
    - Настройки приложения: http://localhost:5000/processing-center/default
    - Консоль H2:
        - URL: http://localhost:8080/h2-console
        - JDBC URL: jdbc:h2:mem:processing-center

### Shell commands
  - Get a course: gc
  - Make authorization: ma --tid <terminal_id> --date <date_operation> --cardNumber <card_number> --expDate <card_validity_period> --sum <transaction_amount> --currency <operation's currency>
  - Get card's balance: gcb --cardNumber <card_number>
  - Get card's status: gcs --cardNumber <card_number>

### Тестирование
1. Отключить Spring Shell в тестах в test\java\resources\application.properties: spring.shell.interactive.enabled=false, spring.main.allow-circular-references=true
2. В test\java\resources\ скопировать только data.sql (файл schema.sql в ресурсы тестов переносить нельзя иначе тесты будут тестировать не то, что находится в базе!)

### Статьи по теме
1. Справочник MCC кодов https://mcc-codes.ru/
2. Коды ошибок Visa/MasterCard/МИР https://wiki.mandarin.io/pages/viewpage.action?pageId=5014410
3. Коды ответов Visa и MasterCard https://how-helper.ru/kody-otvetov-visa-mastercard/
4. Банк России. Технические ресурсы https://www.cbr.ru/development/
5. Получение ежедневных курсов валют (как DataSet) https://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx?op=GetLatestDateTime
6. Messaging with Spring AMQP https://www.baeldung.com/spring-amqp
7. Tutorial: one-spring-amqp https://www.rabbitmq.com/tutorials/tutorial-one-spring-amqp.html
8. RabbitMQ Spring tutorial (rus) https://habr.com/ru/post/262069/
9. RabbitMQ. Часть 1. Introduction. Erlang, AMQP https://habr.com/ru/post/488654/
10. gRPC — альтернатива REST API от Google https://www.youtube.com/watch?v=SMy4CaxizbA
11. Spring Boot With H2 Database https://www.baeldung.com/spring-boot-h2-database