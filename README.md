[![Java](https://img.shields.io/badge/Java-E43222??style=for-the-badge&logo=Java&logoColor=FFFFFF)](https://java.com/)
[![Apache Maven](https://img.shields.io/badge/Apache_Maven-F7F7F7??style=for-the-badge&logo=Apache&logoColor=C85D38)](https://maven.apache.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-boot/)
[![Spring Shell](https://img.shields.io/badge/Spring_Shell-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-shell/)
[![Spring Cloud Config](https://img.shields.io/badge/Spring_Cloud_Config-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-cloud-config/)
[![Spring Cloud Discovery](https://img.shields.io/badge/Spring_Cloud_Discovery-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/guides/gs/service-registration-and-discovery/)
[![Spring Cloud OpenFeign](https://img.shields.io/badge/Spring_Cloud_OpenFeign-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-cloud-openfeign)
[![Spring Web](https://img.shields.io/badge/Spring_Web-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/guides/gs/serving-web-content/)
[![Resilience4j](https://img.shields.io/badge/Resilience4j-FFFFFF??style=for-the-badge&logo=Resilience4j&logoColor=2E64A4)](https://resilience4j.readme.io/)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-FFFFFF??style=for-the-badge&logo=Thymeleaf&logoColor=025B10)](https://www.thymeleaf.org/)
[![Spring Security](https://img.shields.io/badge/Spring_Security-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-security/)
[![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-FFFFFF??style=for-the-badge&logo=Spring)](https://spring.io/projects/spring-data-jpa)
[![Docker](https://img.shields.io/badge/Docker-0E2B62??style=for-the-badge&logo=Docker&logoColor=FFFFFF)](https://www.docker.com/)
[![H2](https://img.shields.io/badge/H2-0618D5??style=for-the-badge&logo=H2&logoColor=FFFFFF)](https://www.h2database.com/)
[![RabbitMQ](https://img.shields.io/badge/RabbitMQ-FFFFFF??style=for-the-badge&logo=rabbitmq)](https://www.rabbitmq.com/)
[![Jasypt](https://img.shields.io/badge/Jasypt-FFFFFF??style=for-the-badge&logo=Jasypt&logoColor=2E64A4)](http://www.jasypt.org/)
[![gRPC](https://img.shields.io/badge/gRPC-FFFFFF??style=for-the-badge&logo=gRPC&logoColor=2E64A4)](https://grpc.io/)

### "Модуль авторизации банковского платежного процессинга на микросервисной архитектуре" bank-payment-processing-services
Bank payment processing on microservice architecture (processing-center, issuing-bank, sales-point)
Репозитории:
1. Проект https://bit.ly/bpps-ms
2. Конфигурация https://bit.ly/bpps-config
3. Диаграмма развертывания https://bit.ly/bpps-diagram

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

### Microservice 6: "Конвертер курсов валют" currency-converter
1. Основной функционал: выполнение конверсионных операций из одной валюты в другую по актуальному курсу

### Docker
  - docker pull rabbitmq:management 
  - Настройки image в docker-compose.yaml
  - Запуск RabbitMQ: docker-compose up

### Заметки
Для включения Feign в микросервисах: (36 - 1:14:49)
  - spring-cloud-starter-openfeign (OpenFeign SPRING CLOUD ROUTING)
  - @EnableFeignClient

### Запуск проекта
1. Terminal: docker-compose up
2. configuration-server
3. eureka-discovery-server (Edit Configuration - Active profiles: standalone)
4. currency-converter
5. processing-center
6. issuing-bank
7. sales-point

### Тестирование функционала
1. Мониторинг:
  - eureka-discovery-server http://localhost:8001/
  - RabbitMQ Management http://localhost:15672/ (guest/guest)
  - http://localhost:8081/h2-console (JDBC URL: jdbc:h2:mem:issuing-bank)
2. issuing-bank:
  - shell: сс, ca, cbc, gb, gaa, gat, gac, ct
  - shell: sc
3. currency-converter:
  - shell: gc
  - http://localhost:8083/actuator
4. processing-center: 
  - shell: gc
  - Проведение авторизации: http://localhost:8080/authorization/tid/000000001/date/2022-10-26/card/4123450101654724/expdate/1225/sum/500.55/curr/RUB
  - http://localhost:8080/actuator
5. sales-point:
  - shell: ma

### Демонстрация проекта:
1. issuing-bank: cbc - создать новую карту у клиента, ct - создать транзакцию пополнения счета карты, sm - отправить в процессинг новые карты и пополнения счетов
2. sales-point: ma - покупка по карте
3. REST:
   а) успешная авторизация по карте (валюта покупки = валюте карты) http://localhost:8080/authorization/tid/000000001/date/2022-10-26/card/4123450101654724/expdate/1225/sum/500.55/curr/RUB
   б) неудачаная авторизация, карта заблокирована: http://localhost:8080/authorization/tid/000000001/date/2022-10-26/card/5123459858074128/expdate/1225/sum/14/curr/RUB
   в) авторизация с запросом курса валюты (валюта покупки отличается от валюты карты): http://localhost:8080/authorization/tid/000000001/date/2022-10-26/card/4750657776370372/expdate/1225/sum/14/curr/RUB

### Статьи по теме:
1. 5 диаграмм, необходимых для документирования архитектуры решений https://habr.com/ru/company/epam_systems/blog/538018/
