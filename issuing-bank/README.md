### Microservice 3: "Банк-эмитент" issuing-bank
1. Основной функционал: ведение актуальной базы клиентов, ведение актуальной базы банковских продуктов клиентов
   (банковские карты VISA, MasterCard, Мир), ведение операций по счетам банковских карт, обмен информацией с процессинговым
   центром (эмиссия карт, транзакции)
2. Диаграмма развертывания: https://bit.ly/bpps-diagram
3. ERD-диаграмма отношений сущностей: https://bit.ly/bpps-erd-ib
4. Зависимости:
  - spring-cloud-starter-config (Config Client SPRING CLOUD CONFIG)
  - spring-cloud-starter-bootstrap (активация загрузки из bootstrap.properties)
  - spring-shell-starter (Spring Shell)
  - spring-boot-starter-web (Spring Web)
  - spring-boot-starter-thymeleaf (Thymeleaf)
  - spring-boot-starter-data-jpa (Spring Data JPA)
  - h2 (H2) примечание: консоль доступна в версии H2 2.1.214
  - spring-boot-starter-amqp (Spring for RabbitMQ MESSAGING)
  - spring-boot-starter-actuator
5. Конфигурация и настройки:
  - Actuator: http://localhost:8081/actuator
  - Настройки приложения: http://localhost:5000/issuing-bank/default
  - Консоль H2:
    - URL: http://localhost:8081/h2-console
    - JDBC URL: jdbc:h2:mem:issuing-bank

### Shell commands
  - Вывести все команды: help
  - Справка по выбранной команде: help сс 

  - Create a new client: сс --lastname <last_name> --firstname <first_name> --middlename <middle_name> --birthdate <birth_date> --document <document> --address <address> --phone <phone>   
  - Create an account: ca --clientId <client_id> --currencyLetterCode <currency_letter_code>
  - Create a bank card: cbc --clientId <client_id> --currencyLetterCode <currency_letter_code> --paymentSystemId <payment_system_id> 
  - Get account balance: gb --accountId <account_id>
  - Get all client's accounts: gaa --clientId <client_id> 
  - Get all transactions on the account: gat --accountId <account_id>
  - Get all client's cards: gac --clientId <client_id>
  - Create a transaction: ct --accountId <account_id> --transactionTypeId <transaction_type_id> --sum <sum> --transactionName <transaction_name> 

  - Send messages (new cards and transactions) to processing-center: sm

### Тестирование
1. Отключить Spring Shell в тестах в test\java\resources\application.properties: spring.shell.interactive.enabled=false, spring.main.allow-circular-references=true
2. В test\java\resources\ скопировать только data.sql (файл schema.sql в ресурсы тестов переносить нельзя иначе тесты будут тестировать не то, что находится в базе!)

### Статьи по теме
1. Spring Boot Best Practices for Developers https://bit.ly/3Smr8rg
2. Знакомьтесь: банковская карта https://finance.sberuniversity.ru/bcw/2
3. Номер банковской карты https://www.banki.ru/wikibank/nomer_bankovskoy_kartyi/
4. Платежные системы https://www.banki.ru/wikibank/platejnyie_sistemyi/
5. Цифровые и буквенные коды, наименования валют, наименования стран и территорий https://bit.ly/3TD6jZB
6. Валюты стран мира на английском языке - полный список https://bit.ly/3sbOvsX
7. H2 Data Types http://www.h2database.com/html/datatypes.html
8. Тестовые карты (testcards) https://docs.assist.ru/pages/viewpage.action?pageId=5767473
9. Messaging with Spring AMQP https://www.baeldung.com/spring-amqp
10. Tutorial: one-spring-amqp https://www.rabbitmq.com/tutorials/tutorial-one-spring-amqp.html
11. RabbitMQ Spring tutorial (rus) https://habr.com/ru/post/262069/
12. RabbitMQ. Часть 1. Introduction. Erlang, AMQP https://habr.com/ru/post/488654/
13. Spring Boot With H2 Database https://www.baeldung.com/spring-boot-h2-database
14. Большой гайд по Optional в Java https://struchkov.dev/blog/optional-in-java/
15. Обзор работы с опционалами (Optional Java) https://hashnets.com/optional-java-overview/
16. Hibernate could not initialize proxy – no Session https://www.baeldung.com/hibernate-initialize-proxy-exception
17. Spring Data JPA and Named Entity Graphs https://www.baeldung.com/spring-data-jpa-named-entity-graphs
18. Транслитерация имени и фамилии для загранпаспорта http://translit-online.ru/pasport.html
19. Как по номеру карты определить платежную систему https://www.banki.ru/blog/BAY/8991.php
20. BIN банковской карты https://pikabu.ru/story/bin_bankovskoy_kartyi_4961206
21. Сервис определения банка по номеру карты https://psm7.com/bin-card
22. Алгоритм ключевания расчетного счета http://bit.ly/3A8SHOC