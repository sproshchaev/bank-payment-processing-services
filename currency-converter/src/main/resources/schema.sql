drop table if exists currency;
create table currency
(
    id                    bigint auto_increment primary key,
    currency_digital_code varchar(3),
    currency_letter_code  varchar(3),
    currency_name         varchar(255)
);

drop table if exists exchange_rate;
create table exchange_rate
(
    id               bigint auto_increment primary key,
    response_date    timestamp,
    currency_id_from bigint,
    currency_id_to   bigint,
    rate             double,
    foreign key (currency_id_from) references currency (id),
    foreign key (currency_id_to) references currency (id)
);
