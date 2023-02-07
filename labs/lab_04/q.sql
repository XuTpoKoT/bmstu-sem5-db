CREATE
LANGUAGE plpython3u;

-- Скалярная CLR
-- Преобразование заказчика в JSON
CREATE OR REPLACE FUNCTION json_customer (customer_id uuid)
    RETURNS text
    AS $$
import json
res = plpy.execute(f"""SELECT * 
FROM customer 
WHERE id = '{customer_id}'""")
cus = ""
if res.nrows() != 0:
	cus = json.dumps(res[0])
return cus
$$
LANGUAGE plpython3u;

SELECT json_customer ('62222f6e-728b-4543-99ce-a9efee161953');

-- Агрегатная CLR
-- Агрегатная функция умножения
CREATE OR REPLACE FUNCTION multi_agg (a bigint, b int)
    RETURNS bigint
    AS $$
if a is None:
	return b
if b is None:
	return a
return a * b
$$
LANGUAGE plpython3u;

CREATE AGGREGATE multi (
    STYPE = bigint,
    SFUNC = multi_agg,
    BASETYPE = int
);

SELECT
    multi (cnt_products)
FROM (
    SELECT
        cnt_products
    FROM
        order_productmodel op
    LIMIT 4) x;
	
-- Табличная CLR
-- Получить список заказчиков, подходящих по ФИО
drop FUNCTION get_customer(text, text);
CREATE OR REPLACE FUNCTION get_customer_by_names(first_name text DEFAULT NULL, last_name text DEFAULT NULL)
    RETURNS TABLE (
        id uuid,
        first_name text,
        last_name text
    )
    AS $$
query = "SELECT id, first_name, last_name from customer"
if any((first_name, last_name)):
	query += " WHERE "
	if first_name:
		query += f" first_name = '{first_name}' "
	if last_name:
		query += f" and last_name = '{last_name}' "
query += ";"
res = plpy.execute(query)
return res
$$
LANGUAGE plpython3u;

select *
from get_customer_by_names('Dimon');

-- Хранимую процедуру CLR
CREATE OR REPLACE PROCEDURE change_storage_cnt_clr(productmodel_name text, new_cnt int)
AS $$
	
plpy.execute(f"""UPDATE productmodel
    			 SET storage_cnt = '{new_cnt}'
    			 WHERE productmodel.name = '{productmodel_name}';""");
$$
LANGUAGE plpython3u;

call change_storage_cnt_clr('CRAFTER MLR-3464', 11);

-- Триггер CLR
CREATE OR REPLACE FUNCTION update_material()
RETURNS TRIGGER 
AS $$
	#old_name = old.name
	#new_name = new.name
	plpy.notice(f'Material was updated ')
$$ LANGUAGE plpython3u;

CREATE TRIGGER update_material_trigger
AFTER UPDATE ON material 
FOR EACH ROW 
EXECUTE FUNCTION update_material();

UPDATE material 
SET name = 'ель'
WHERE name = 'ёль';

UPDATE material 
SET name = 'ёль'
WHERE name = 'ель';

CREATE OR REPLACE FUNCTION del_category()
RETURNS TRIGGER 
AS $$
	plpy.notice(f'You cant delete category!')
$$ LANGUAGE plpython3u;

CREATE VIEW category_view AS 
SELECT * from category;

CREATE TRIGGER del_category_trigger
INSTEAD OF DELETE ON category_view 
FOR EACH ROW 
EXECUTE FUNCTION del_category();

delete from category_view;

-- Тип данных
CREATE TYPE fullname AS (
    first_name text,
    last_name text)
    
CREATE OR REPLACE FUNCTION get_customer_by_fullname ("name" fullname)
    RETURNS TABLE (
        id text,
        first_name text,
        last_name text
    )
    AS $$
query = "SELECT id, first_name, last_name FROM customer"
if any([name[key] for key in name]):
	query += " WHERE "
	if name['first_name']:
		query += f" first_name = '{name['first_name']}' "
	if name['last_name']:
		query += f" AND last_name = '{name['last_name']}' "
query += ";"
res = plpy.execute(query)
return res
$$
LANGUAGE plpython3u;
    
select * from get_customer_by_fullname (ROW ('Dimon', NULL));
select * from get_customer_by_fullname (ROW ('Dimon', 'King'));


   
   
   
   