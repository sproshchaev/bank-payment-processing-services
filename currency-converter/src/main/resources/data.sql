insert into currency (currency_digital_code, currency_letter_code, currency_name)
values ('643', 'RUB', 'Russian Ruble'),
       ('980', 'UAH', 'Hryvnia'),
       ('840', 'USD', 'US Dollar'),
       ('978', 'EUR', 'Euro'),
       ('392', 'JPY', 'Yen'),
       ('156', 'CNY', 'Yuan Renminbi'),
       ('826', 'GBP', 'Pound Sterling');

insert into exchange_rate (response_date, currency_id_from, currency_id_to, rate)
values ('2022-11-15 16:05:25.007', 3, 1, 60.215015),
       ('2022-11-15 16:09:18.542', 1, 3, 0.016607);
