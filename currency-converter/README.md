### Microservice 6: "Конвертер курсов валют" currency-converter
1. Основной функционал: выполнение конверсионных операций из одной валюты в другую по курсам, публикуемым на сайте ЦБ РФ
2. Зависимости:
  - spring-cloud-starter-config (Config Client SPRING CLOUD CONFIG)
  - spring-cloud-starter-bootstrap (активация загрузки из bootstrap.properties)
  - spring-shell-starter (Spring Shell)
  - spring-boot-starter-web (Spring Web)
  - spring-boot-starter-thymeleaf (Thymeleaf)
  - spring-boot-starter-data-jpa (Spring Data JPA)
  - h2 (H2) примечание: консоль доступна в версии H2 2.1.214
3. Конфигурация и настройки:
   - Настройки приложения: http://localhost:5000/currency-converter/default
   - Консоль H2:
     - URL: http://localhost:8083/h2-console
     - JDBC URL: jdbc:h2:mem:currency-converter

### Таблицы currency-converter 
  UML https://dbdiagram.io/d/6360c97f5170fb6441d6d4e3

### Сервисы актуальных курсов валют
1. Fixer https://apilayer.com/marketplace/fixer-api?live_demo=show
2. Currency Data API https://apilayer.com/marketplace/currency_data-api?txn=free&live_demo=show

### Статьи по теме
1. Обзор сервисов для получения актуальных курсов валют https://habr.com/ru/post/537784/
2. Как управлять вашими секретами с git-crypt https://habr.com/ru/company/nixys/blog/570306/
3. Как правильно работать с сѝкретами в проекте https://medium.com/maddevs-io/secrets-513d41eaaf43
