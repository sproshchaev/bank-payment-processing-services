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
  - spring-boot-starter-actuator
5. Конфигурация и настройки:
    - Actuator: http://localhost:8080/actuator
    - Настройки приложения: http://localhost:5000/processing-center/default
    - Консоль H2:
        - URL: http://localhost:8080/h2-console
        - JDBC URL: jdbc:h2:mem:processing-center

### Shell commands
  - Get a course: gc
  - Make authorization: ma --tid <terminal_id> --date <date_operation> --cardNumber <card_number> --expDate <card_validity_period> --sum <transaction_amount> --currency <operation's currency>
  - Get card's status: gcs --cardNumber <card_number>
  - Get card's balance: gcb --cardNumber <card_number>
  - Send messages (new cards and transactions) to the issuing-bank: sm
  - Get a course: gc
  - Get card's balance in currency: gcbc --cardNumber <card_number> --currency <currency_letter_code> 

### Используемые коды ответов (errorCode) (https://wiki.mandarin.io/pages/viewpage.action?pageId=5014410)
1. "00" - "Approved (Успешная транзакция)"
2. "03" - "Invalid merchant or service provider (Недействительный идентификатор продавца)" 
3. "14" - "Invalid card (no such number) (Эмитент указывает, что эта карта недействительна)"
4. "51" - "Not sufficient funds (Недостаточно средств на карте)"
5. "54" - "Expired card (Срок действия карты истек)"
6. "56" - "No card record (Нет такой карты)"
7. "76" - "Invalid 'to' account (Неверный счет. Дебетового счета не существует"
8. "96" - "System malfunction  (Произошла системная ошибка)"

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
12. Spring Integrations Message Routing https://docs.spring.io/spring-integration/reference/html/message-routing.html
13. Округление чисел в Java https://javarush.ru/groups/posts/2773-okruglenie-chisel-v-java
14. Stackoverflow.com - parseDouble in Java results to NumberFormatException http://bit.ly/3O3ScuX
15. Учимся избегать null-значений в современном Java. Часть 2 http://bit.ly/3Adiyoj
16. Hibernate Tip: Create an EntityGraph with multiple SubGraphs https://thorben-janssen.com/hibernate-tip-entitygraph-multiple-subgraphs/
17. Введение в Spring Boot Actuator https://habr.com/ru/company/otus/blog/452624/
18. Обзор работы с опционалами (Optional Java) https://hashnets.com/optional-java-overview/
19. Введение в AOP в Spring Boot https://sysout.ru/vvedenie-v-aop-v-spring-boot/

