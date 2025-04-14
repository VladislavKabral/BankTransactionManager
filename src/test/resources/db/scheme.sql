create table limits (
    id uuid primary key,
    type int not null,
    value numeric(10, 2) not null,
    datetime timestamp with time zone not null
);

create table transactions (
    id uuid primary key,
    account_from varchar(10) not null check ( account_from ~ '^[0-9]*$' ),
    account_to varchar(10) not null check ( account_from ~ '^[0-9]*$' ),
    currency_shortname char(3) not null check(char_length(currency_shortname) = 3),
    sum numeric (10, 2) not null,
    sum_usd numeric (10, 2) not null,
    expense_category int not null,
    datetime timestamp with time zone not null,
    limit_id uuid references limits (id)
);

create table currency_rates (
    id uuid primary key,
    source char(3) not null check(char_length(source) = 3),
    target char(3) not null check(char_length(target) = 3),
    rate numeric(10, 4) not null,
    datetime timestamp not null
);