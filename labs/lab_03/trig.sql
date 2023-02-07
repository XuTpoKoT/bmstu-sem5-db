select o.customer_id , o."date" 
from orders o
where o.customer_id = 'be0cd344-84e8-4053-a003-fb19bf23e20b';

drop function update_customer cascade;
-- Триггер AFTER
CREATE OR REPLACE FUNCTION update_customer()
RETURNS TRIGGER 
AS $$
begin
	if old.first_name != new.first_name then
		RAISE NOTICE 'New name =  %', new.first_name;
    	RAISE NOTICE 'After Old name =  %', old.first_name;
    end if;
	
	RETURN new;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_customer_trigger
AFTER UPDATE ON customer 
FOR EACH ROW 
EXECUTE FUNCTION update_customer();

UPDATE customer 
SET first_name = 'Dimn'
WHERE first_name = 'Dimn';

-- Триггер before
--drop function del_order_trigger cascade;
--drop trigger del_order_trigger on orders;
create or replace function del_order_trigger()
returns trigger 
as $$
begin
    --raise notice 'old =  %', old;

   	with prod_cnt_in_order as (
   		select min(cnt_products) as mcp
   		from order_productmodel op 
		where op.order_id = old.id
		limit 1)
   	update productmodel
   	set storage_cnt = storage_cnt + (select mcp from prod_cnt_in_order);
   return new;
end
$$ language plpgsql;

create trigger del_order
before delete on orders 
for each row 
execute function del_order_trigger();

delete from orders o
	where o.id = 'cfd72a81-cdf8-4357-bf2a-ce753048917a';

select from orders o
	where o.id = 'cfd72a81-cdf8-4357-bf2a-ce753048917a';

select from order_productmodel op 
	where op.order_id  = 'cfd72a81-cdf8-4357-bf2a-ce753048917a';

select max(storage_cnt)
	from productmodel p ;

-- Триггер INSTEAD OF
CREATE VIEW customer_view AS 
SELECT * from customer;

drop function update_customer2 cascade;
CREATE OR REPLACE FUNCTION update_customer2()
RETURNS TRIGGER 
AS $$
begin
	if old.first_name != new.first_name then
		RAISE NOTICE 'New name =  %', new.first_name;
    	RAISE NOTICE 'Instead Old name =  %', old.first_name;
    end if;
	
	RETURN new;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_customer_trigger2
INSTEAD OF UPDATE ON customer_view 
FOR EACH ROW 
EXECUTE FUNCTION update_customer2();

UPDATE customer_view 
SET first_name = 'Diman'
WHERE first_name = 'Dimon';

select * from customer_view where first_name = 'Dimon';
