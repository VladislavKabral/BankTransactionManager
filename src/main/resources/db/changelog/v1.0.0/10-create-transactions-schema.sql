create table transactions (
    id uuid primary key,
    account_from varchar(10) not null check ( account_from ~ '^[0-9]*$' ),
    account_to varchar(10) not null check ( account_from ~ '^[0-9]*$' ),
    currency_shortname char(3) not null check(char_length(currency_shortname) = 3),
    sum numeric (10, 2) not null,
    expense_category varchar(10) not null,
    datetime timestamp with time zone not null
)