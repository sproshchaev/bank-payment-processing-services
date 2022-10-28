[![Java](https://img.shields.io/badge/Java-E43222??style=for-the-badge&logo=Java&logoColor=FFFFFF)](https://java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-boot/)
[![Spring Shell](https://img.shields.io/badge/Spring_Shell-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-shell/)
[![Spring Cloud Config](https://img.shields.io/badge/Spring_Cloud_Config-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-cloud-config/)
[![Spring Cloud OpenFeign](https://img.shields.io/badge/Spring_Cloud_OpenFeign-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-cloud-openfeign)
[![Spring Web](https://img.shields.io/badge/Spring_Web-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/guides/gs/serving-web-content/)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-FFFFFF??style=for-the-badge&logo=Thymeleaf&logoColor=025B10)](https://www.thymeleaf.org/)
[![Spring Security](https://img.shields.io/badge/Spring_Security-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-security/)
[![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-data-jpa)
[![H2](https://img.shields.io/badge/H2-0618D5??style=for-the-badge&logo=H2&logoColor=FFFFFF)](https://www.h2database.com/)

### "Модуль авторизации банковского платежного процессинга на микросервисной архитектуре" bank-payment-processing-services
Bank payment processing on microservice architecture (processing-center, issuing-bank, sales-point)
Репозитории:
1. Проект https://bit.ly/bpps-ms
2. Конфигурация https://bit.ly/bpps-config

### Microservice 1: "Конфигурационный сервер" configuration-server
Основной функционал: управление файлами application.properties микросервисов на Github https://bit.ly/bpps-config

### Microservice 2: "Процессинговый центр" processing-center
Основной функционал: ведение актуальной базы банковских карт VISA, MasterCard, Мир с платежными лимитами, доступными
  для совершения операций, авторизация покупок по банковским картам VISA, MasterCard, Мир в торговых точках (один
  процессинг на несколько торговых точек). Обмен информацией об эмиссии и транзакциях с банками-эмитентами (один 
  процессинг на несколько банков-эмитентов)

### Microservice 3: "Банк-эмитент" issuing-bank
Основной функционал: ведение актуальной базы клиентов, ведение актуальной базы банковских продуктов клиентов
  (банковские карты VISA, MasterCard, Мир), ведение операций по счетам банковских карт, обмен информацией 
  с процессинговым центром (эмиссия карт, транзакции)

### Microservice 4: "Торговая точка" sales-point
Основной функционал: формирование заказа клиента со списком покупок, авторизация платежа за покупки по банковским
  картам VISA, MasterCard, Мир в процессинговом центре, возврат средств на карту клиента при отказе от покупки 
  (возврате товара)

### Microservice 5: "Сервисный регистратор" eureka-discovery-server
1. Основной функционал: service registry (аналоги Consul, Zookeeper)
2. Зависимости:
  - spring-cloud-starter-netflix-eureka-server (Eureka Server SPRING CLOUD DISCOVERY)

### Заметки
Для включения Feign в микросервисах: (36 - 1:14:49)
  - spring-cloud-starter-openfeign (OpenFeign SPRING CLOUD ROUTING)
  - @EnableFeignClient

### Статьи по теме:
1. Spring Boot With H2 Database https://www.baeldung.com/spring-boot-h2-database
