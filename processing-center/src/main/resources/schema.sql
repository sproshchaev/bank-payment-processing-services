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

drop table if exists currency;
create table currency
(
    id                    bigint auto_increment primary key,
    currency_digital_code varchar(3),
    currency_letter_code  varchar(3),
    currency_name         varchar(255)
);

drop table if exists issuing_bank;
create table issuing_bank
(
    id               bigint auto_increment primary key,
    bic              varchar(9),
    bin              varchar(5),
    abbreviated_name varchar(255)
);

drop table if exists acquiring_bank;
create table acquiring_bank
(
    id               bigint auto_increment primary key,
    bic              varchar(9),
    abbreviated_name varchar(255)
);

drop table if exists sales_point;
create table sales_point
(
    id                bigint auto_increment primary key,
    pos_name          varchar(255),
    pos_address       varchar(255),
    pos_inn           varchar(12),
    acquiring_bank_id bigint,
    foreign key (acquiring_bank_id) references acquiring_bank (id)
);

drop table if exists merchant_category_code;
create table merchant_category_code
(
    id       bigint auto_increment primary key,
    mcc      varchar(4),
    mcc_name varchar(255)
);

drop table if exists terminal;
create table terminal
(
    id          bigint auto_increment primary key,
    terminal_id varchar(9),
    mcc_id      integer,
    pos_id      bigint,
    foreign key (mcc_id) references merchant_category_code (id),
    foreign key (pos_id) references sales_point (id)
);

drop table if exists response_code;
create table response_code
(
    id                bigint auto_increment primary key,
    error_code        varchar(2),
    error_description varchar(255),
    error_level       varchar(255)
);

drop table if exists transaction_type;
create table transaction_type
(
    id                    bigint auto_increment primary key,
    transaction_type_name varchar(255),
    operator              varchar(1)
);

drop table if exists account;
create table account
(
    id              bigint auto_increment primary key,
    account_number  varchar(50),
    balance         numeric,
    currency_id     bigint,
    issuing_bank_id bigint,
    foreign key (currency_id) references currency (id),
    foreign key (issuing_bank_id) references issuing_bank (id)
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
    foreign key (card_status_id) references card_status (id),
    foreign key (payment_system_id) references payment_system (id),
    foreign key (account_id) references account (id)
);

drop table if exists transaction;
create table transaction
(
    id                  bigint auto_increment primary key,
    transaction_date    date,
    sum                 numeric,
    transaction_name    varchar(255),
    transaction_type_id bigint,
    card_id             bigint,
    terminal_id         bigint,
    response_code_id    bigint,
    authorization_code  varchar(6),
    foreign key (transaction_type_id) references transaction_type (id),
    foreign key (card_id) references card (id),
    foreign key (terminal_id) references terminal (id),
    foreign key (response_code_id) references response_code (id)
);

