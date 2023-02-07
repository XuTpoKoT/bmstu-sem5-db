TASK_2_1 = """
-- 1. Найти сотрудников, опоздавших сегодня меньше чем на 5 минут
with q1 as (
	SELECT DISTINCT fio, min(etime) as t
	FROM employee e 
	JOIN employee_visit ev on e.id = ev.employee_id
	WHERE type = 1 AND ev.edate = '2022-12-17'
	group by fio)
select fio, t 
from q1
where t > '09:00:00' AND  t < '09:05:00';
"""

TASK_2_2 = """
-- 2. Найти сотрудников, которые выходили больше чем на 10 минут
select fio 
from employee e
where e.id in (
    select employee_id 
    from (select employee_id, etime as time_start, type, 
        lead(etime) over (partition by employee_id, edate order by etime) as time_finish
    	from employee_visit ev
		) as kpp_exits
	where time_finish is not null and time_finish - time_start > '10 minutes' and type = 2);
"""

TASK_2_3 = """
-- 3. Найти сотрудников бухгалтерии (Dep1), приходящих на работу раньше 8:00
SELECT DISTINCT fio
FROM employee e JOIN employee_visit ev on e.id = ev.employee_id
WHERE department = 'Dep1' AND type = 1 AND etime < '08:00:00';
"""
