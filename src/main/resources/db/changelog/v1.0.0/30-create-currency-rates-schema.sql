create table currency_rates (
    id uuid primary key,
    source char(3) not null check(char_length(source) = 3),
    target char(3) not null check(char_length(target) = 3),
    rate numeric(10, 4) not null,
    datetime timestamp not null
)