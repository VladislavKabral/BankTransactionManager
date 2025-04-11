create table limits (
    id uuid primary key,
    type varchar(10) not null,
    value numeric(10, 2) not null,
    datetime timestamp with time zone not null
)