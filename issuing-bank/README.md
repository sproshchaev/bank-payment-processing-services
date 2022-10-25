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

### Таблицы issuing-bank 
  UML https://dbdiagram.io/d/635237194709410195ae1708

### Статьи по теме
1. Spring Boot Best Practices for Developers https://bit.ly/3Smr8rg
2. Знакомьтесь: банковская карта https://finance.sberuniversity.ru/bcw/2
3. Номер банковской карты https://www.banki.ru/wikibank/nomer_bankovskoy_kartyi/
4. Платежные системы https://www.banki.ru/wikibank/platejnyie_sistemyi/
5. Цифровые и буквенные коды, наименования валют, наименования стран и территорий https://bit.ly/3TD6jZB
6. Валюты стран мира на английском языке - полный список https://bit.ly/3sbOvsX
7. H2 Data Types http://www.h2database.com/html/datatypes.html
8. Тестовые карты (testcards) https://docs.assist.ru/pages/viewpage.action?pageId=5767473