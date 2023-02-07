-- 1. Извлечь данные из таблиц
-- Функция для преобразования заданной таблицы в JSON формат
CREATE OR REPLACE FUNCTION table2json (table_name regclass)
    RETURNS SETOF jsonb
    AS $$
BEGIN
    RETURN query EXECUTE format('select row_to_json(%s)::jsonb from %s', table_name, table_name);
END;
$$
LANGUAGE plpgsql;

-- 2. Выгрузка таблиц в JSON
SELECT
    table2json ('orders');

GRANT SELECT ON customer TO PUBLIC;
  
COPY (
    SELECT
        table2json ('customer'))
TO '/mnt/lab_05/customer_dump.json';

CREATE TEMP TABLE customer_json_tmp (
    "json" jsonb
);

COPY customer_json_tmp
FROM
    '/mnt/lab_05/customer_dump.json';

DROP TABLE customer_json_tmp;
DROP TABLE customer_from_json;
CREATE TEMP TABLE customer_from_json AS
SELECT
    t.*
FROM
    customer_json_tmp,
    jsonb_populate_record(NULL::customer, "json") t;
select * from employee;
-- Сравнение таблиц
SELECT
    *
FROM
    customer_json_tmp;

SELECT
    *
FROM
    customer;

-- Сравенение количества элементов
SELECT
    COUNT(*)
FROM
    customer_from_json;

SELECT
    COUNT(*)
FROM
    customer;

-- 3. Создать таблицу...
COPY (
    SELECT
        table2json ('orders'))
TO '/mnt/lab_05/orders_dump.json';

-- Создание таблицы с JSON
CREATE TABLE orders_json (
    id uuid DEFAULT gen_random_uuid (),
    doc jsonb NOT NULL
);

-- Загрузка данных в таблицу
CREATE TEMP TABLE orders_json_tmp (
    "json" jsonb
);

TRUNCATE orders_json_tmp;

TRUNCATE orders_json;

COPY orders_json_tmp
FROM
    '/mnt/lab_05/orders_dump.json';

-- Вставка при помощи команды INSERT
INSERT INTO orders_json (doc)
SELECT
    "json"
FROM
    orders_json_tmp;

SELECT
    *
FROM
    orders_json;

-- 4. Выбрать конкретный аттрибут
CREATE TABLE customers_json (
    id uuid DEFAULT gen_random_uuid (),
    doc jsonb NOT NULL
);
INSERT INTO customers_json (doc)
SELECT
    "json"
FROM
    customer_json_tmp;

SELECT
    *
FROM
    customers_json;
-- 1+2. Извлечь фрагмент JSON + Выбрать конкретный аттрибут
SELECT
    doc -> 'email' mail
FROM
    customers_json;

-- 3. Проверка существования аттрибута
SELECT
    count(*)
FROM
    customers_json
WHERE
    doc ? 'email';

-- 4. Внести изменения в JSON
UPDATE
    customers_json
SET
    doc = jsonb_set(doc, '{last_name}', '"OLIVER3"'::jsonb)
WHERE
    doc @> '{"email":"patricia33@example.com"}';
 
SELECT
    doc
FROM
    customers_json
WHERE
    doc @> '{"email":"patricia33@example.com"}';

--- 5. Разделить документ на несколько строк
SELECT
    *
FROM
    jsonb_each((
        SELECT
            doc
        FROM customers_json LIMIT 1)) x;
       
CREATE TEMP TABLE products_json_tmp (
    "json" jsonb
);


CREATE temp TABLE special_date_orders (
    first_name text,
    last_name text,
    special_date text
);
INSERT INTO special_date_orders (first_name, last_name, special_date) (
	select c.first_name, c.last_name,
		case date_part('year', o."date")
			when  date_part('year', current_date) then 'This year'
			when date_part('year', current_date) - 1 then 'Last year' 
			else concat((date_part('year', current_date) - date_part('year', o."date"))::text, ' years ago'::text)
		end as yearstr
	from customer c
		join orders o on c.id = o.customer_id);
	
SELECT
    *
FROM
    jsonb_each((
        select *
        from table2json ('special_date_orders') limit 5)) X;
		

			





