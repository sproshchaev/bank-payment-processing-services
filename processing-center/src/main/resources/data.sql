insert into card_status (card_status_name)
values ('Card is not active'),
       ('Card is valid'),
       ('Card is temporarily blocked'),
       ('Card is lost'),
       ('Card is compromised');


insert into payment_system (payment_system_name)
values ('VISA International Service Association'),
       ('Mastercard'),
       ('JCB'),
       ('American Express'),
       ('Diners Club International'),
       ('China UnionPay');

insert into currency (currency_digital_code, currency_letter_code, currency_name)
values ('643', 'RUB', 'Russian Ruble'),
       ('980', 'UAH', 'Hryvnia'),
       ('840', 'USD', 'US Dollar'),
       ('978', 'EUR', 'Euro'),
       ('392', 'JPY', 'Yen'),
       ('156', 'CNY', 'Yuan Renminbi'),
       ('826', 'GBP', 'Pound Sterling');

insert into issuing_bank (bic, bin, abbreviated_name)
values ('041234569', '12345', 'ПАО Банк-эмитент №1'),
       ('041234570', '12346', 'ПАО Банк-эмитент №2'),
       ('041234571', '12347', 'ПАО Банк-эмитент №3');

insert into acquiring_bank (bic, abbreviated_name)
values ('041234567', 'ПАО Банк-эквайер №1'),
       ('041234568', 'ПАО Банк-эквайер №2'),
       ('041234569', 'ПАО Банк-эквайер №3');

insert into account (account_number, balance, currency_id, issuing_bank_id)
values ('40817810123456789012', 649.7, 1, 1),
       ('40817810234567890123', 48702.07, 1, 1),
       ('40817810345678901234', 715000.01, 1, 1);

insert into merchant_category_code (mcc, mcc_name)
values ('5309', 'Беспошлинные магазины Duty Free'),
       ('5651', 'Одежда для всей семьи'),
       ('5691', 'Магазины мужской и женской одежды'),
       ('5812', 'Места общественного питания, рестораны'),
       ('5814', 'Фастфуд');

insert into sales_point (pos_name, pos_address, pos_inn, acquiring_bank_id)
values ('Shop №1', 'City, 1-st 1', '1234567890', 1),
       ('Shop №2', 'City, 2-st 2', '1234567891', 2),
       ('Shop №3', 'City, 3-st 3', '1234567892', 1);

insert into terminal (terminal_id, mcc_id, pos_id)
values ('000000001', 1, 1),
       ('000000002', 2, 2),
       ('000000003', 3, 3);


insert into response_code (error_code, error_description, error_level)
values ('00', 'одобрено и завершено', 'Все в порядке'),
       ('01', 'авторизация отклонена, обратиться в банк-эмитент', 'не критическая'),
       ('03', 'незарегистрированная торговая точка или агрегатор платежей', 'не критическая'),
       ('05', 'авторизация отклонена, оплату не проводить', 'критическая'),
       ('41', 'карта утеряна, изъять', 'критическая'),
       ('51', 'недостаточно средств на счёте', 'сервисная или аппаратная ошибка'),
       ('55', 'неправильный PIN', 'не критическая');

insert into transaction_type (transaction_type_name, operator)
values ('Пополнение счета', '+'),
       ('Списание со счета', '-');

insert into card (card_number, expiration_date, holder_name, card_status_id, payment_system_id, account_id, received_from_issuing_bank, sent_to_issuing_bank)
values ('4123450101654724', '2025-12-31', 'IVAN I. IVANOV', 2, 1, 1, '2022-10-21 15:26:06.175', '2022-10-21 15:27:08.271'),
       ('5123459858074128', '2025-12-31', 'SEMION E. PETROV', 3, 2, 2, '2022-04-05 10:23:05.372', '2022-04-05 10:24:02.175'),
       ('3123451333300000', '2025-10-31', 'DMITRY S. SIDOROV', 2, 3, 3, '2022-10-20 12:21:07.273', '2022-10-20 12:22:01.471');

insert into transaction (transaction_date, sum, transaction_name, account_id, transaction_type_id, card_id, terminal_id, response_code_id, authorization_code)
values ('2022-10-22', 1000.11, 'Cash deposit', 1, 1, 1, null, null, ''),
       ('2022-04-06', 50000.92, 'Cash deposit', 2, 1, 2, null, null, ''),
       ('2022-10-21', 750000.12, 'Cash deposit', 3, 1, 3, null, null, ''),
       ('2022-10-23', 350.41, 'Money transfer', 1, 2, 1, null, null, ''),
       ('2022-06-23', 1298.85, 'Commission', 2, 2, 2, null, null, ''),
       ('2022-10-22', 35000.11, 'Payment of the invoice', 3, 2, 3, null, null, '');
