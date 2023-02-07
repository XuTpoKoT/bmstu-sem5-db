from peewee import *
from requests_sql import *
from datetime import *

con = PostgresqlDatabase(
	database='rk3_db',
	user='postgres',
	password='postgres',
	host='localhost',
	port=5432
)


class BaseModel(Model):
	class Meta:
		database = con

class Employee(BaseModel):
	id = IntegerField(column_name='id')
	fio = CharField(column_name='fio')
	date_of_birth = DateField(column_name='date_of_birth')
	department = CharField(column_name='department')

	class Meta:
		table_name = 'employee'

class EmployeeVisit(BaseModel):
	id = IntegerField(column_name='id')	
	employee_id = ForeignKeyField(Employee, backref='employee_id')
	edate = DateField(column_name='edate')
	day_of_week =  CharField(column_name='day_of_week')
	etime = TimeField(column_name='etime')
	e_type = IntegerField(column_name='type')	

	class Meta:
		table_name = 'employee_visit'


def output(cur):
	rows = cur.fetchall()
	for elem in rows:
		print(*elem)
	print()

def task_2():
	global con

	cur = con.cursor()

	print("1. Найти сотрудников, опоздавших сегодня меньше чем на 5 минут")
	cur.execute(TASK_2_1)
	output(cur)

	print("\n2. Найти сотрудников, которые выходили больше чем на 10 минут")
	cur.execute(TASK_2_2)
	output(cur)

	print("\n3. Найти сотрудников бухгалтерии (Dep1), приходящих на работу раньше 8:00")
	cur.execute(TASK_2_3)
	output(cur)

	cur.close()

def print_query(query):
	for elem in query.dicts().execute():
		print(elem)

def task_2_orm():
	print("1. Найти сотрудников, опоздавших сегодня меньше чем на 5 минут")
	query = Employee.select(Employee.fio, fn.Min(EmployeeVisit.etime).alias('t')).join(EmployeeVisit).where(EmployeeVisit.e_type==1).group_by(Employee.fio)
	t1 = time(hour=9, minute=3)
	res = [e.t for e in query if e.t < t1] #.where(EmployeeVisit.etime < '09:05:00').where(EmployeeVisit.edate == datetime.now())
	print(res)
	print_query(query)
	 
	print("\n2. Найти сотрудников, которые выходили больше чем на 10 минут")

	print("\n3. Найти сотрудников бухгалтерии (Dep1), приходящих на работу раньше 8:00")
	query = Employee.select(Employee.fio).join(EmployeeVisit).where(
		EmployeeVisit.etime < '08:00:00').where(EmployeeVisit.e_type==1).where(Employee.department=='Dep1')

	print_query(query)


if __name__ == "__main__":
	print("===========SQL========")
	task_2()
	print("===========ORM========")
	task_2_orm()

con.close()
