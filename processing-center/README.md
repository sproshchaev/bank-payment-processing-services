### Microservice 2: "Процессинговый центр" processing-center
1. Основной функционал: ведение актуальной базы банковских карт VISA, MasterCard, Мир с платежными лимитами, доступными
   для совершения операций, авторизация покупок по банковским картам VISA, MasterCard, Мир в торговых точках (один
   процессинг на несколько торговых точек). Обмен информацией об эмиссии и транзакциях с банками-эмитентами (один процессинг
   на несколько банков-эмитентов)
2. Зависимости:
  - spring-cloud-starter-config (Config Client SPRING CLOUD CONFIG)
  - spring-cloud-starter-bootstrap (активация загрузки из bootstrap.properties)
  - Spring Shell
  - Spring Web
  - Spring Data JPA
  - PostgreSQL
3. Конфигурация и настройки:
    - Консоль H2:
        - URL: http://localhost:8080/h2-console
        - JDBC URL: jdbc:h2:mem:processing-center

### Таблицы processing-center
  UML https://dbdiagram.io/d/635616c6fa2755667d572886

### Статьи по теме
1. Справочник MCC кодов https://mcc-codes.ru/
2. Коды ошибок Visa/MasterCard/МИР https://wiki.mandarin.io/pages/viewpage.action?pageId=5014410
3. Коды ответов Visa и MasterCard https://how-helper.ru/kody-otvetov-visa-mastercard/