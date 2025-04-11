create table limits (
    id uuid primary key,
    value numeric(10, 2) not null,
    datetime timestamp with time zone not null
)