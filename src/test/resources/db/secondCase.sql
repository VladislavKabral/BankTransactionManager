insert into currency_rates(id, source, target, rate, datetime)
values
    ('8b898bee-16ee-4601-acaa-78ca8ac2928a', 'USD', 'KZT', '516.9003', '2025-04-13 11:11:51.215937'),
    ('80604ac5-f04c-4999-9a78-96fe18fec864', 'USD', 'RUB', '83.2303', '2025-04-13 11:11:51.335478'),
    ('872bf39f-d9af-4487-8e01-dab023f5c385', 'USD', 'EUR', '0.8800', '2025-04-13 11:11:51.411264');

insert into limits(id, type, value, datetime)
values
    ('d8c2a2f7-74fa-437c-aa69-ce471ade0df8', '0', '1000.00', '2022-01-01 01:00:00.196000 +00:00'),
    ('e4d94d0f-29df-4216-b7db-28912ed396a2', '0', '400.00', '2022-01-10 01:00:00.196000 +00:00');

insert into transactions(id, account_from, account_to, currency_shortname, sum, sum_usd, expense_category, datetime, limit_id)
values
    ('699e7bbe-cf5e-41bc-ad4a-63ca363a847a', '1234567890', '0000011115', 'USD', '500.00', '500.00', '0', '2022-01-02 01:00:00.196000 +00:00', 'd8c2a2f7-74fa-437c-aa69-ce471ade0df8'),
    ('efcc19bb-6fff-4451-ae8b-32b4788af10c', '1234567890', '0000011115', 'USD', '100.00', '100.00', '0', '2022-01-03 01:00:00.196000 +00:00', 'd8c2a2f7-74fa-437c-aa69-ce471ade0df8'),
    ('644aafe1-36ef-4163-b331-b42d2f6d57f7', '1234567890', '0000011115', 'USD', '100.00', '100.00', '0', '2022-01-11 01:00:00.196000 +00:00', 'e4d94d0f-29df-4216-b7db-28912ed396a2'),
    ('3273bf02-d3aa-4f97-94f6-4ee989bf5bf6', '1234567890', '0000011115', 'USD', '100.00', '100.00', '0', '2022-01-12 11:00:00.000000 +00:00', 'e4d94d0f-29df-4216-b7db-28912ed396a2');