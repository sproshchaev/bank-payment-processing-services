[![Java](https://img.shields.io/badge/Java-E43222??style=for-the-badge&logo=Java&logoColor=FFFFFF)](https://java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-boot/)
[![Spring Shell](https://img.shields.io/badge/Spring_Shell-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-shell/)
[![Spring Cloud Config](https://img.shields.io/badge/Spring_Cloud_Config-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-cloud-config/)
[![Spring Web](https://img.shields.io/badge/Spring_Web-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/guides/gs/serving-web-content/)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-FFFFFF??style=for-the-badge&logo=Thymeleaf&logoColor=025B10)](https://www.thymeleaf.org/)
[![Spring Security](https://img.shields.io/badge/Spring_Security-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-security/)
[![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-data-jpa)
[![H2](https://img.shields.io/badge/H2-0618D5??style=for-the-badge&logo=H2&logoColor=FFFFFF)](https://www.h2database.com/)

### "Банковский платежный процессинг на микросервисной архитектуре" bank-payment-processing-services
Bank payment processing on microservice architecture (processing-center, issuing-bank, sales-point)
Репозитории:
1. Проект https://bit.ly/bpps-ms
2. Конфигурация https://bit.ly/bpps-config

### Microservice 1: "Конфигурационный сервер" configuration-server
1. Основной функционал: управление файлами application.properties микросервисов на Github https://bit.ly/bpps-config
2. Зависимости:
  - Config Server (SPRING CLOUD CONFIG): spring-cloud-config-server
3. Конфигурация и настройки:
  - Main: @EnableConfigServer
  - bootstrap.properties

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
  - H2
3. Конфигурация и настройки:
  - Консоль H2:
    - URL: http://localhost:8080/h2-console
    - JDBC URL: jdbc:h2:mem:processing-center

### Microservice 3: "Банк-эмитент" issuing-bank
1. Основной функционал: ведение актуальной базы клиентов, ведение актуальной базы банковских продуктов клиентов
   (банковские карты VISA, MasterCard, Мир), ведение операций по счетам банковских карт, обмен информацией с процессинговым
   центром (эмиссия карт, транзакции)
2. Зависимости:
  - spring-cloud-starter-config (Config Client SPRING CLOUD CONFIG)
  - spring-cloud-starter-bootstrap (активация загрузки из bootstrap.properties)
  - Spring Shell
  - Spring Web (Spring RestTemplate)
  - Thymeleaf
  - Spring Data JPA
  - H2
3. Конфигурация и настройки:
  - Консоль H2:
    - URL: http://localhost:8081/h2-console
    - JDBC URL: jdbc:h2:mem:issuing-bank

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

### Статьи по теме:
1. Spring Boot With H2 Database https://www.baeldung.com/spring-boot-h2-database
