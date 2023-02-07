select * from customers;

select "date" from orders;

Select * from guitars;

Select * from guitar_models;

Select * from guitar_types;

Select * from manufacturers;

Select * from materials;

Select * from sound_pickups sp ;

select * from order_statuses os ;

--1 Инструкция SELECT, использующая предикат сравнения.
-- Выбрать гитары определённой фирмы
select gm."name",  m."name", gm.price 
from guitar_models gm
	join manufacturers m on gm.manufacturer = m.id 
where m."name" = 'TAKAMINE';

--2 Инструкция SELECT, использующая предикат BETWEEN.
-- Выбрать заказчиков и их заказы за определённый период
select o."date",  c.first_name, c.last_name
from orders o
	join customers c on o.customer_id  = c.id 
where o."date" between '2015-01-01' and '2018-12-31';

--3 Инструкция SELECT, использующая предикат LIKE.
--Выбрать заказчиков с email на .com
select gm."name", c.first_name, c.last_name, c.email 
from guitar_models gm 
	join customers c on o.customer_id  = c.id 
where c.email like '%.com';

--4 Инструкция SELECT, использующая предикат IN с вложенным подзапросом.
--Выбрать гитары из определённого материала
select gm."name", gm.body_material 
from guitar_models gm
where gm.body_material in(
	select m.id
	from materials m
	where m."name" = 'Махагони');

--5 Инструкция SELECT, использующая предикат EXISTS с вложенным подзапросом.
--Выбрать гитары, которые присутствуют в заказах
select *
from guitars g
where exists(
	select *
	from orders o
    where o.guitar_id = g.id)

--6 Инструкция SELECT, использующая предикат сравнения с квантором.
--Выбрать гитары, цена которых выше цены любой гитары TAKAMINE
select gm."name", gm.price 
from guitar_models gm
where price > all(
	select price 
	from guitar_models gm2 
		join manufacturers m on gm2.manufacturer = m.id 
	where m."name" = 'TAKAMINE');

--7 Инструкция SELECT, использующая агрегатные функции в выражениях столбцов.
-- Суммарная стоимость заказов
select o.id, sum(gm.price) as total_price
from orders o 
	join guitars g on o.guitar_id = g.id
	join guitar_models gm on g.model = gm.id
group by o.id
order by total_price desc;

--8 Инструкция SELECT, использующая скалярные подзапросы в выражениях столбцов.
-- Суммарная стоимость всех заказов каждого заказчика.
select * from (
select first_name, last_name,(
	select sum(gm.price)
	from orders o
		join guitars g on o.guitar_id = g.id 
		join guitar_models gm on g.model = gm.id 
	where c.id = customer_id
	group by customer_id) as total_price
from customers c
order by total_price desc) x
where total_price is not null;


--9? Инструкция SELECT, использующая простое выражение CASE.
--Выбрать заказчиков и их заказы, выводя год заказа в определённом формате
select c.first_name, c.last_name, o.id, 
	case date_part('year', o."date")
		when  date_part('year', current_date) then 'This year'
		when date_part('year', current_date) - 1 then 'Last year' 
		else concat((date_part('year', current_date) - date_part('year', o."date"))::text, ' years ago'::text)
	end as yearstr
from customers c
	join orders o on c.id = o.customer_id;

--10. Инструкция SELECT, использующая поисковое выражение CASE.
--Выбрать гитары и их цены в определённом формате
select gm."name",
case
	when price < 10000 then 'Дешёвая'
	when price < 15000 then 'Средней цены'
	else 'Дорогая'
end as pricestr
from guitar_models gm;

--11. Создание новой временной локальной таблицы из результирующего набора данных инструкции SELECT.
--Бессмысленный запрос
select avg(gm.price) as "Average guitar price", m."name" 
into firstlocaltable
from guitar_models gm
	join materials m on gm.body_material = m.id
group by m."name";

select * from firstlocaltable;
drop table firstlocaltable;

--12. Инструкция SELECT, использующая вложенные коррелированные подзапросы в качестве производных таблиц в предложении FROM.
-- Все гитары натурального цвета
select "name", color, frets
from guitars g
	join (
		select *
		from guitar_models gm
		where color = 'Натуральный') as gmn
		on g.model = gmn.id;

--13. Инструкция SELECT, использующая вложенные подзапросы с уровнем вложенности 3.
-- Все заказы с гитарами натурального цвета
select *
from orders o 
where guitar_id in(
	select guitar_id
	from guitars g
		join (
			select *
			from guitar_models gm
			where color = 'Натуральный') as gmn
			on g.model = gmn.id);


--14. Инструкция SELECT, консолидирующая данные с помощью предложения GROUP BY, но без предложения HAVING.
--Кол-во гитар с определённым типов звукоснимателя
select sp."name", count(*)
from guitar_models gm 
	join sound_pickups sp on gm.sound_pickup = sp.id 
group by sp."name";

--15. Инструкция SELECT, консолидирующая данные с помощью предложения
-- Средняя цена модели каждого производителя > 15000
select m."name", avg(gm.price)
from guitar_models gm 
	join manufacturers m on gm.manufacturer = m.id 
group by m."name"
having avg(gm.price) > 15000;

--16. Однострочная инструкция INSERT, выполняющая вставку в таблицу одной строки значений.
insert into customers(first_name, last_name, birth_date, email, phone)
values('Dima', 'Morozov', '2002-08-17', 'jimmoroz@gmail.com', '+7-123-456-78-99')

--17. Многострочная инструкция INSERT, выполняющая вставку в таблицу результирующего набора данных вложенного подзапроса.
-- Вставка нового заказа
insert into orders(customer_id, guitar_id, "date", status)
values((
	select id
	from customers
	where first_name = 'Dima' limit 1),(
	select id
	from guitars limit 1), current_date, (
	select id 
	from order_statuses 
	where "name" = 'Оформлен'));

--18. Простая инструкция UPDATE.
update customers  
set phone = '+7-333-444-56-71'
where first_name = 'Dima';

--19. Инструкция UPDATE со скалярным подзапросом в предложении SET.
-- Бессмысленный запрос
update guitar_models 
set price = (
	select max(price)
	from guitar_models)
where manufacturer = '078a9bdf-5fa6-45db-8407-0b8f8e375093';

--20. Простая инструкция DELETE.
DELETE FROM customers 
WHERE first_name = 'Dima';

--21. Инструкция DELETE с вложенным коррелированным подзапросом в предложении WHERE.
-- Удалить все заказы Дим
delete from orders 
where customer_id in
(
    select id
    from customers
    where first_name = 'Dima'
);

--22. Инструкция SELECT, использующая простое обобщенное табличное выражение
-- Бессмысленный запрос
with customer_full_names as (
select first_name, last_name
from customers)
select customer_full_names.first_name
from customer_full_names;

--23. Инструкция SELECT, использующая рекурсивное обобщенное табличное выражение.
CREATE TABLE geo (
    id int not null primary key, 
    parent_id int references geo(id),  
    name varchar(1000)
);

INSERT INTO geo 
(id, parent_id, name) 
VALUES 
(1, null, 'Планета Земля'),
(2, 1, 'Континент Евразия'),
(3, 1, 'Континент Северная Америка'),
(4, 2, 'Европа'),
(5, 4, 'Россия'),
(6, 4, 'Германия'),
(7, 5, 'Москва'),
(8, 5, 'Санкт-Петербург'),
(9, 6, 'Берлин');

WITH RECURSIVE r AS (
   SELECT id, parent_id, name, 1 AS level
   FROM geo
   WHERE id = 4

   UNION ALL

   SELECT geo.id, geo.parent_id, geo.name, r.level + 1 AS level
   FROM geo
      JOIN r
          ON geo.parent_id = r.id
)

SELECT * FROM r;

--24. Оконные функции. Использование конструкций MIN/MAX/AVG OVER()
select gm."name", avg(price) over(partition by m."name")
from guitar_models gm 
	join manufacturers m on gm.manufacturer = m.id;

--25. Оконные фнкции для устранения дублей
CREATE TABLE test( name VARCHAR NOT NULL, surname VARCHAR NOT NULL, age INTEGER);
INSERT INTO test (name, surname, age) VALUES 
('Ann', 'Kosenko', 12), ('Brian', 'Shaw', 22), ('Brian', 'Shaw', 22), ('Brian', 'Shaw', 22), ('Ann', 'Kosenko', 12);

SELECT * FROM test 

WITH test_deleted AS(DELETE FROM test RETURNING *),
test_inserted AS(SELECT name, surname, age, ROW_NUMBER() OVER(PARTITION BY name, surname, age ORDER BY name, surname, age) 
 				 rownum FROM test_deleted)INSERT INTO test SELECT name, surname, age
				 FROM test_inserted WHERE rownum = 1;
				
SELECT * FROM test
DROP TABLE test

--
select gm.color, count(*)
from orders o
	join guitars g on o.guitar_id = g.id 
	join guitar_models gm on g.model = gm.id 
where gm.color = 'Белый' or gm.color = 'Чёрный'
group by gm.color;












