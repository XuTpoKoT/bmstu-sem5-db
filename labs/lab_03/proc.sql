-- 1. Хранимую процедуру без параметров или с параметрами
-- Изменить кол-во товара на складе.
CREATE OR REPLACE PROCEDURE change_storage_cnt(productmodel_name text, new_cnt int)
AS $$
	UPDATE public.productmodel  
    SET storage_cnt = new_cnt
    WHERE "name" = productmodel_name;
$$ language sql;

call change_storage_cnt('CRAFTER MLR-3464', 5);

select * from public.productmodel p where "name" = 'CRAFTER MLR-3464';

-- 2. Рекурсивную хранимую процедуру или хранимую процедур с рекурсивным ОТВ
create or replace procedure fib_proc(first int, second int, max int = 21)
as $$
begin
    raise notice 'elem = %', first;
    if second <= max then
        call fib_proc(second, first + second, max);
    end if;
end
$$ language plpgsql;

call fib_proc(3, 4);

-- 3. Хранимую процедуру с курсором
-- Одним из способов возврата результатов работы хранимых процедур является 
-- формирование результирующего множества. Данное множество формируется при 
-- выполнении оператора SELECT. Оно записывается во временную таблицу - курсор.
CREATE OR REPLACE PROCEDURE get_products_by_manufacturer(manufacturer_name text)
AS $$
DECLARE
	product_model_name text;
    myCursor CURSOR 
	FOR
        SELECT p."name" 
		FROM productmodel p
			join manufacturer m on p.manufacturer = m.id
		where m."name" = manufacturer_name;
BEGIN
    OPEN myCursor;
    LOOP
        -- FETCH - Получает следующую строку из курсора
        -- И присваевает в переменную, которая стоит после INTO.
        -- Если строка не найдена (конец), то присваевается значение NULL.
        FETCH myCursor
        INTO product_model_name;
        -- Выходим из цикла, если нет больше строк (Т.е. конец).
        EXIT WHEN NOT FOUND;
        RAISE NOTICE 'product_model_name =  %', product_model_name;
    END LOOP;
    CLOSE myCursor;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE PROCEDURE get_products_by_manufacturer2(manufacturer_name text)
AS $$
DECLARE
	product_model_name text;
BEGIN
	for product_model_name IN
        SELECT p."name" 
		FROM productmodel p
			join manufacturer m on p.manufacturer = m.id
		where m."name" = manufacturer_name
	loop
        RAISE NOTICE 'product_model_name =  %', product_model_name;
    END LOOP;
END;
$$ LANGUAGE plpgsql;

CALL get_products_by_manufacturer2('FENDER'); 

-- 4.
CREATE OR REPLACE PROCEDURE get_column_types(table_name_ text)
AS $$
DECLARE
	el RECORD;
BEGIN
	FOR el IN
		SELECT column_name, data_type
		FROM information_schema.columns
        WHERE table_name = table_name_
	LOOP
		RAISE NOTICE 'el = %', el;
	END LOOP;
END
$$ LANGUAGE plpgsql;

call get_column_types('orders');


