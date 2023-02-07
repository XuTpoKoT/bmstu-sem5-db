
CREATE TABLE region (
    id bigserial primary key,
    name text NOT NULL,
    descr text
);

insert into region(name, descr)
	values ('r1', 'rd1');
insert into region(name, descr)
	values ('r2', 'rd2');
insert into region(name, descr)
	values ('r3', 'rd3');
insert into region(name, descr)
	values ('r4', 'rd4');
insert into region(name, descr)
	values ('r5', 'rd5');
insert into region(name, descr)
	values ('r6', 'rd6');
insert into region(name, descr)
	values ('r7', 'rd7');
insert into region(name, descr)
	values ('r8', 'rd8');
insert into region(name, descr)
	values ('r9', 'rd9');
insert into region(name, descr)
	values ('r10', 'rd10');

select * from region;

CREATE TABLE sanat (
    id bigserial primary key,
    reg_id bigint references region(id) ON DELETE CASCADE,
    name text NOT NULL,
    fyear int,
    descr text
);

insert into sanat(reg_id, name, fyear)
	values (1, 's1', 2001);
insert into sanat(reg_id, name, fyear)
	values (1, 's2', 2000);
insert into sanat(reg_id, name, fyear)
	values (1, 's3', 2001);
insert into sanat(reg_id, name, fyear)
	values (2, 's4', 2004);
insert into sanat(reg_id, name, fyear)
	values (2, 's5', 2004);
insert into sanat(reg_id, name, fyear)
	values (2, 's6', 2001);
insert into sanat(reg_id, name, fyear)
	values (3, 's7', 2002);
insert into sanat(reg_id, name, fyear)
	values (3, 's8', 2002);
insert into sanat(reg_id, name, fyear)
	values (3, 's9', 2001);
insert into sanat(reg_id, name, fyear)
	values (3, 's10', 2003);

select * from sanat;

CREATE TABLE tourist (
    id bigserial primary key,
    fname text NOT NULL,
    byear int,
    address text,
    email text
);

insert into tourist(fname, byear)
	values ('fn1', 2001);
insert into tourist(fname, byear)
	values ('fn2', 2000);
insert into tourist(fname, byear)
	values ('fn3', 2003);
insert into tourist(fname, byear)
	values ('fn4', 2007);
insert into tourist(fname, byear)
	values ('fn5', 2008);
insert into tourist(fname, byear)
	values ('fn6', 2001);
insert into tourist(fname, byear)
	values ('fn7', 2000);
insert into tourist(fname, byear)
	values ('fn8', 2003);
insert into tourist(fname, byear)
	values ('fn9', 2007);
insert into tourist(fname, byear)
	values ('fn10', 2008);

select * from tourist;

CREATE TABLE t_s (
    t_id bigint references tourist(id) ON DELETE CASCADE,
    s_id bigint references sanat(id) ON DELETE CASCADE
);

insert into t_s(t_id, s_id)
	values (1, 1);
insert into t_s(t_id, s_id)
	values (1, 2);
insert into t_s(t_id, s_id)
	values (1, 3);
insert into t_s(t_id, s_id)
	values (2, 1);
insert into t_s(t_id, s_id)
	values (2, 2);
insert into t_s(t_id, s_id)
	values (3, 3);
insert into t_s(t_id, s_id)
	values (4, 4);
insert into t_s(t_id, s_id)
	values (5, 5);
insert into t_s(t_id, s_id)
	values (6, 6);
insert into t_s(t_id, s_id)
	values (7, 7);
insert into t_s(t_id, s_id)
	values (8, 8);
insert into t_s(t_id, s_id)
	values (9, 9);
insert into t_s(t_id, s_id)
	values (10, 10);

select * from t_s;

--Задание 2

--Запрос 1: Select с поисковым case. получение имени санатория и словесного описания его года
select s.name,
	case s.fyear
		when 2000 then 'Old sanatory'
		when 2004 then 'New sanatory' 
		else 'not old nor new sanatory' 
	end as yearstr
from sanat s;
	
--Запрос 2: update со скалярным подзапросом в предложении set. Замена даты рождения отдыхающего с id 1 на максимальный год основания санатория.
update tourist 
set byear = (
	select max(fyear)
	from sanat)
where id = 1;

--Запрос 3:
select * from sanat;
select reg_id, max(fyear) m
from sanat 
group by reg_id
having  > 2002;


create or replace procedure myind(dbn text, tablen text)
as $$
begin
    raise notice 'dbn = %', dbn;
    select *
	from pg_indexes
	where tablename = tablen and datname ;
end
$$ language plpgsql;


