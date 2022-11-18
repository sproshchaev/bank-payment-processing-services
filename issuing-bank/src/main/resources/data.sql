insert into bank_setting (setting, current_value, description)
values
       ('license', 'Генеральная лицензия Банка России на осуществление банковских операций №____ от _______ г.', 'Лицензия'),
       ('full_name', 'Публичное акционерное общество «Банк-эмитент номер один»', 'Полное наименование банка'),
       ('abbreviated_name', 'ПАО Банк-эмитент №1', 'Сокращенное наименование банка'),
       ('registration_address', '________, ________, ________, ул.________, д.___', 'Юридический адрес'),
       ('postal_address', '________, ________, ________, ул.________, д.___', 'Почтовый адрес'),
       ('correspondent_account', '30101810____________', 'Корреспондентский счет'),
       ('central_bank', '________', 'Отделение Центрального банка'),
       ('bic', '041234569', 'Банковский идентификационный код (БИК)'),
       ('bin', '12345', 'Идентификационный номер эмитента (БИН карты)'),
       ('code_1', '123456789', 'КПП'),
       ('code_2', '123456789', 'ИНН'),
       ('code_3', '1234567890123', 'ОГРН'),
       ('code_4', '12345678', 'ОКПО'),
       ('code_5', '123456789', 'ОКТМО'),
       ('code_6', '12.34', 'ОКВЭД'),
       ('code_7', '1234567', 'ОКОГУ'),
       ('code_8', '12', 'ОКФС'),
       ('code_9', '12345', 'ОКОПФ'),
       ('code_10', '12345678901', 'ОКАТО'),
       ('swift', '123456789', 'SWIFT-код'),
       ('e_mail', '123@456.com', 'E-mail'),
       ('www', 'www.123.com', 'Сайт'),
       ('registration_date', '________', 'Дата внесения в ЕГРЮЛ');

insert into card_status (card_status_name)
values ('Card is not active'),
       ('Card is valid'),
       ('Card is temporarily blocked'),
       ('Card is lost'),
       ('Card is compromised');

insert into payment_system (payment_system_name, first_digit_bin)
values ('VISA International Service Association', '4'),
       ('Mastercard', '5'),
       ('JCB', '3'),
       ('American Express', '3'),
       ('Diners Club International', '3'),
       ('China UnionPay', '6');

insert into account_type (account_type_name)
values ('Active account'),
       ('Passive account');

insert into currency (currency_digital_code, currency_letter_code, currency_digital_code_account, currency_name)
values ('643', 'RUB', '810', 'Russian Ruble'),
       ('980', 'UAH', '980', 'Hryvnia'),
       ('840', 'USD', '840', 'US Dollar'),
       ('978', 'EUR', '978', 'Euro'),
       ('392', 'JPY', '392', 'Yen'),
       ('156', 'CNY', '156', 'Yuan Renminbi'),
       ('826', 'GBP', '826', 'Pound Sterling');

insert into transaction_type (transaction_type_name)
values ('Debit'),
       ('Credit');

insert into client (last_name, first_name, middle_name, birth_date, document, address, phone, email)
values ('Иванов', 'Иван', 'Иванович', '1980-01-30', '1234 123456 выдан 01.01.2000 УВД г.Москвы', 'Москва, ул.Шаболовская, 37 кв. 20', '+79221234567', 'pronats@inbox.ru'),
       ('Петров', 'Семен', 'Егорович', '1989-12-31', '5678 789012 выдан 20.10.2012 УВД г.Санкт-Петербурга', 'Санкт-Петербург, Лиговский пр-т, 96 кв. 15', '+79121234568', 'ivanivanov@list.ru'),
       ('Сидоров', 'Дмитрий', 'Степанович', '1976-10-20', '9012 345678 выдан 16.07.2018 УВД г.Тюмени', 'Тюмень, ул.Республики, 21 кв. 12', '+79041234569', 'dmsydorov@mail.ru');

insert into account (account_number, balance, currency_id, account_type_id, client_id, account_opening_date, suspending_operations)
values ('40817810800000000001', 649.7, 1, 2, 1, '2022-10-21', false),
       ('40817810100000000002', 48702.07, 1, 2, 2, '2022-04-05', false),
       ('40817810400000000003', 715000.01, 1, 2, 3, '2022-10-20', false),
       ('40817840000000000004', 10000.0, 3, 2, 1, '2022-10-21', false);

insert into card (card_number, expiration_date, holder_name, card_status_id, payment_system_id, account_id, client_id, sent_to_processing_center, received_from_processing_center)
values ('4123450101654724', '2025-12-31', 'IVAN I. IVANOV', 2, 1, 1, 1, '2022-10-21 12:10:33.695', '2022-10-21 13:12:12.159'),
       ('5123459858074128', '2025-12-31', 'SEMION E. PETROV', 3, 2, 2, 2, '2022-04-05 11:58:45.690', '2022-04-05 12:59:54.199'),
       ('3123451333300000', '2025-10-31', 'DMITRY S. SIDOROV', 2, 3, 3, 3, '2022-10-20 12:22:32.234', '2022-10-20 13:42:22.143'),
       ('4750657776370372', '2025-12-31', 'IVAN I. IVANOV', 2, 1, 4, 1, '2022-10-21 12:19:19.143', '2022-10-21 13:24:39.648');

insert into transaction (transaction_date, sum, transaction_name, account_id, transaction_type_id, sent_to_processing_center)
values ('2022-10-22', 1000.11, 'Cash deposit', 1, 2, '2022-10-22'),
       ('2022-04-06', 50000.92, 'Cash deposit', 2, 2, '2022-04-06'),
       ('2022-10-21', 750000.12, 'Cash deposit', 3, 2, '2022-10-21'),
       ('2022-10-23', 350.41, 'Money transfer', 1, 1, '2022-10-23'),
       ('2022-06-23', 1298.85, 'Commission', 2, 1, '2022-06-23'),
       ('2022-10-22', 35000.11, 'Payment of the invoice', 3, 1, '2022-10-22'),
       ('2022-10-22', 10000.0, 'Cash deposit', 4, 2, '2022-10-22');