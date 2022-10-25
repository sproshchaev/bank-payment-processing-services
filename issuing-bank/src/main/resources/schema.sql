drop table if exists bank_setting;
create table bank_setting
(
    id            smallint auto_increment primary key,
    setting       varchar(100),
    current_value varchar(255),
    description   varchar(255)
);

drop table if exists card_status;
create table card_status
(
    id               bigint auto_increment primary key,
    card_status_name varchar(255)
);

drop table if exists payment_system;
create table payment_system
(
    id                  bigint auto_increment primary key,
    payment_system_name varchar(50)
);

drop table if exists account_type;
create table account_type
(
    id                bigint auto_increment primary key,
    account_type_name varchar(255)
);

drop table if exists currency;
create table currency
(
    id                    bigint auto_increment primary key,
    currency_digital_code varchar(3),
    currency_letter_code  varchar(3),
    currency_name         varchar(255)
);

drop table if exists transaction_type;
create table transaction_type
(
    id                    bigint auto_increment primary key,
    transaction_type_name varchar(255)
);

drop table if exists client;
create table client
(
    id          bigint auto_increment primary key,
    last_name   varchar(100),
    first_name  varchar(100),
    middle_name varchar(100),
    birth_date  date,
    document    varchar(255),
    address     varchar(255),
    phone       varchar(20)
);

drop table if exists account;
create table account
(
    id                    bigint auto_increment primary key,
    account_number        varchar(50),
    balance               numeric,
    currency_id           bigint,
    account_type_id       bigint,
    client_id             bigint,
    account_opening_date  date,
    suspending_operations boolean,
    account_closing_date  date,
    foreign key (currency_id) references currency (id),
    foreign key (account_type_id) references account_type (id),
    foreign key (client_id) references client (id)
);

drop table if exists card;
create table card
(
    id                bigint auto_increment primary key,
    card_number       varchar(50),
    expiration_date   date,
    holder_name       varchar(50),
    card_status_id    bigint,
    payment_system_id bigint,
    account_id        bigint,
    client_id         bigint,
    foreign key (card_status_id) references card_status (id),
    foreign key (payment_system_id) references payment_system (id),
    foreign key (account_id) references account (id),
    foreign key (client_id) references client (id)
);

drop table if exists transaction;
create table transaction
(
    id                  bigint auto_increment primary key,
    transaction_date    date,
    sum                 numeric,
    transaction_name    varchar(255),
    transaction_type_id bigint,
    account_id          bigint,
    foreign key (transaction_type_id) references transaction_type (id),
    foreign key (account_id) references account (id)
);