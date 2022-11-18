insert into user_access (user_login, user_password, full_name, user_role)
values ('administrator', '12345678', 'Administrator''s full name', 'administrator'),
       ('manager', '123456', 'Manager''s full name', 'user');

insert into payment_system (payment_system_name)
values ('VISA International Service Association'),
       ('Mastercard'),
       ('JCB'),
       ('American Express'),
       ('Diners Club International'),
       ('China UnionPay');

insert into acquiring_bank (bic, abbreviated_name)
values ('041234567', 'ПАО Банк-эквайер №1');

insert into sales_point (pos_name, pos_address, pos_inn, acquiring_bank_id)
values ('Shop №1', 'City, 1-st 1', '1234567890', 1);

insert into merchant_category_code (mcc, mcc_name)
values ('5309', 'Беспошлинные магазины Duty Free'),
       ('5651', 'Одежда для всей семьи'),
       ('5691', 'Магазины мужской и женской одежды'),
       ('5812', 'Места общественного питания, рестораны'),
       ('5814', 'Фастфуд');

insert into terminal (terminal_id, mcc_id, pos_id)
values ('000000001', 1, 1);

insert into transaction_type (transaction_type_name, operator)
values ('Refund', '-'),
       ('Payment', '+');

insert into response_code (error_code, error_description, error_level)
values ('00', 'Approved (Успешная транзакция)', 'Все в порядке'),
       ('03', 'Invalid merchant or service provider (Недействительный идентификатор продавца)', 'Критическая'),
       ('14', 'Invalid card (no such number) (Эмитент указывает, что эта карта недействительна)', 'Не критическая'),
       ('51', 'Not sufficient funds (Недостаточно средств на карте)', 'Не критическая'),
       ('54', 'Expired card (Срок действия карты истек)', 'Критическая'),
       ('56', 'No card record (Нет такой карты)', 'Критическая'),
       ('76', 'Invalid "to" account (Неверный счет. Дебетового счета не существует', 'Сервисная или аппаратная ошибка'),
       ('96', 'System malfunction  (Произошла системная ошибка)', 'Сервисная или аппаратная ошибка');
