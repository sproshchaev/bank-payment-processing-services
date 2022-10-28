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
   - Консоль H2:
     - URL: http://localhost:8081/h2-console
     - JDBC URL: jdbc:h2:mem:issuing-bank

### Таблицы currency-converter 
  UML 

### Статьи по теме
