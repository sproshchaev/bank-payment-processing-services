### Microservice 5: "Сервисный регистратор" eureka-discovery-server
1. Основной функционал: service registry (аналоги Consul, Zookeeper)
2. Диаграмма развертывания: https://bit.ly/bpps-diagram
3. Зависимости:
  - spring-cloud-starter-netflix-eureka-server (Eureka Server SPRING CLOUD DISCOVERY)
4. Аннотоации:
  - @EnableEurekaServer - работает только в main-классе (!)  
5. Запуск сервиса:
  - Edit Configuration (Shift+F4): 
    - Active profiles: standalone

6. Мониторинг сервера: http://localhost:8001/
7. Настройки приложения: http://localhost:5000/eureka-discovery-server/default

### Статьи по теме
1. Service Registration and Discovery https://spring.io/guides/gs/service-registration-and-discovery/
2. Spring Cloud и Spring Boot. Часть 1: использование Eureka Server https://habr.com/ru/company/otus/blog/539348/