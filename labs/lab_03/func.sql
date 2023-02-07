select * from customer c ;

select "date" from orders;

Select * from guitarTypes;

Select * from manufacturer;

Select * from material;

Select * from soundPickup sp ;

select * from orderStatuse os ;

-- 1. Скалярная функция.
-- Возвращает max. цену товара.
CREATE OR REPLACE FUNCTION get_max_product_price()
RETURNS INT AS $$
    SELECT MAX(price)
    FROM productModel;
$$ LANGUAGE sql;

SELECT get_max_product_price() AS max_price;

-- 2. Подставляемую табличную функцию.
-- Возвращает заказчика по first_name.
drop function get_customer;

CREATE OR REPLACE FUNCTION get_customer(first_name TEXT)
RETURNS customer AS $$
    SELECT *
    FROM customer c
    WHERE c.first_name = get_customer.first_name;
$$ LANGUAGE sql;

SELECT * FROM get_customer('Scott');

-- 3. Многооператорную табличную функцию.
-- Вернуть товары двух цветов.
drop function get_product_models_by_color;

CREATE OR REPLACE FUNCTION get_product_models_by_color(color1 TEXT, color2 TEXT)
RETURNS TABLE
(
    out_id uuid,
    out_name  TEXT,
    out_color TEXT
)
AS $$
BEGIN
    -- Добавить к таблице.
    RETURN QUERY
    SELECT id, "name", color
    FROM productModel
    WHERE color=color1;

    RETURN QUERY
    SELECT id, "name", color
    FROM productModel
    WHERE color=color2;
END;
$$ LANGUAGE plpgsql;

select * from get_product_models_by_color('Чёрный', 'Белый');

-- 4. Рекурсивная функция.
-- Числа Фибоначи
create or replace function fib(first int, second int, max int = 21)
returns table (fibonacci int)
as $$
begin
    return query
    select first;
    if second <= max then
        return query
        select *
        from fib(second, first + second, max);
    end if;
end
$$ language plpgsql;

select * from fib(1,1, 21);









