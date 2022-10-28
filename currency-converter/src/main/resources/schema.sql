drop table if exists currency;
create table currency
(
    id                    bigint auto_increment primary key,
    currency_digital_code varchar(3),
    currency_letter_code  varchar(3),
    currency_name         varchar(255)
);
