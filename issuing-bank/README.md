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
5. Конфигурация и настройки:
   - Настройки приложения: http://localhost:5000/issuing-bank/default
   - Консоль H2:
     - URL: http://localhost:8081/h2-console
     - JDBC URL: jdbc:h2:mem:issuing-bank

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