create table transactions (
    id uuid primary key,
    account_from numeric(10) not null check(account_from >= 0 and account_from < 1e10),
    account_to numeric(10) not null check(account_to >= 0 and account_to < 1e10),
    sum numeric (10, 2) not null,
    expense_category varchar(10) not null,
    datetime timestamp not null
)