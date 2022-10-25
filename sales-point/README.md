### Microservice 4: "Торговая точка" sales-point
1. Основной функционал: формирование заказа клиента со списком покупок, авторизация платежа за покупки по банковским
   картам VISA, MasterCard, Мир в процессинговом центре, возврат средств на карту клиента при отказе от покупки (возврате
   товара)
2. Зависимости:
  - spring-cloud-starter-config (Config Client SPRING CLOUD CONFIG)
  - spring-cloud-starter-bootstrap (активация загрузки из bootstrap.properties)
  - Spring Shell
  - Spring Web (Spring RestTemplate)
  - Thymeleaf
  - Spring Data JPA
  - H2
  - Spring Security
3. Конфигурация и настройки:
   - Консоль H2:
     - URL: http://localhost:8082/h2-console
     - JDBC URL: jdbc:h2:mem:sales-point

### Таблицы sales-point
  UML https://dbdiagram.io/d/635780c2fa2755667d671cce

1. Таблица order (заказы)
2. Таблица transaction (операции оплаты)
3. Таблица card (данные банковских карт)

### Статьи по теме

