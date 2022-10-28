### Microservice 5: "Сервисный регистратор" eureka-discovery-server
1. Основной функционал: service registry (аналоги Consul, Zookeeper)
2. Зависимости:
  - spring-cloud-starter-netflix-eureka-server (Eureka Server SPRING CLOUD DISCOVERY)
3. Аннотоации:
  - @EnableEurekaServer - работает только в main-классе (!)  
4. Запуск сервиса:
  - Edit Configuration (Shift+F4): 
    - Active profiles: standalone

5. Мониторинг сервера: http://localhost:8001/

### Статьи по теме
1. Service Registration and Discovery https://spring.io/guides/gs/service-registration-and-discovery/
