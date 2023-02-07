-- Задание 1
drop table if exists employee;
CREATE TABLE IF NOT EXISTS employee
(
    id INT PRIMARY KEY,
    fio TEXT,
    date_of_birth DATE,
    department TEXT
);

INSERT INTO employee VALUES (0, 'name1', '2000-07-19', 'Dep1');
INSERT INTO employee VALUES (1, 'name2', '2000-07-19', 'Dep1');
INSERT INTO employee VALUES (2, 'name3', '2000-07-19', 'Dep2');
INSERT INTO employee VALUES (3, 'name4', '2000-07-19', 'Dep2');
INSERT INTO employee VALUES (4, 'name5', '2000-07-19', 'Dep3');
INSERT INTO employee VALUES (5, 'name6', '2000-07-19', 'Dep3');

select * from employee;

drop table if exists employee_visit;
CREATE TABLE IF NOT EXISTS employee_visit
(
    id INT PRIMARY KEY,
    employee_id INT,
    FOREIGN KEY (employee_id) REFERENCES employee(id) on delete cascade,
    edate DATE DEFAULT CURRENT_DATE,
    day_of_week TExt,
    etime TIME DEFAULT CURRENT_TIME,
    type INT CHECK (type >= 1 AND type <= 2)
);

delete from employee_visit;
INSERT INTO employee_visit VALUES (0, 0, CURRENT_DATE, 'Пон', '07:55:00', 1);
INSERT INTO employee_visit VALUES (1, 1, CURRENT_DATE, 'Пон', '08:50:00', 1);
INSERT INTO employee_visit VALUES (2, 2, CURRENT_DATE, 'Пон', '09:03:00', 1);
INSERT INTO employee_visit VALUES (3, 3, CURRENT_DATE, 'Пон', '09:20:00', 1);

INSERT INTO employee_visit VALUES (4, 0, CURRENT_DATE, 'Пон', '18:55:00', 2);
INSERT INTO employee_visit VALUES (5, 1, CURRENT_DATE, 'Пон', '18:50:00', 2);
INSERT INTO employee_visit VALUES (6, 2, CURRENT_DATE, 'Пон', '19:10:00', 2);
INSERT INTO employee_visit VALUES (7, 3, CURRENT_DATE, 'Пон', '19:20:00', 2);
INSERT INTO employee_visit VALUES (8, 3, '2022-12-17', 'Пон', '09:30:00', 2);
INSERT INTO employee_visit VALUES (9, 3, '2022-12-17', 'Пон', '09:50:00', 1);

-- Задание 1 часть 2.
-- Функция, возвращающая сотрудников, не пришедших сегодня на работу.
-- Сегодня передавать в качестве параметра
CREATE OR REPLACE FUNCTION get_employees_late(dt DATE)
returns table
(
    fio text,
    department text
)
AS
$$
    SELECT fio, department
    FROM employee empl
    WHERE empl.id NOT IN 
    	(SELECT e.id
    	FROM employee e 
    	JOIN employee_visit ev ON e.id = ev.employee_id
    	WHERE ev.edate = dt)
$$ LANGUAGE sql;

SELECT * from get_employees_late(CURRENT_DATE);


-- 2.2 task
select fio 
from employee e
where e.id in (
    select employee_id 
    from (select employee_id, etime as time_start, type, 
        lead(etime) over (partition by employee_id, edate order by etime) as time_finish
    	from employee_visit ev
		) as kpp_exits
	where time_finish is not null and time_finish - time_start > '10 minutes' and type = 2);

with q1 as (
	SELECT DISTINCT fio, min(etime) as t
	FROM employee e 
	JOIN employee_visit ev on e.id = ev.employee_id
	WHERE type = 1 AND ev.edate = '2022-12-17'
	group by fio)
select fio, t 
from q1
where t > '09:00:00' AND  t < '09:05:00';

