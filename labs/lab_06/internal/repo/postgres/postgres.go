package postgres

import (
	"github.com/jmoiron/sqlx"
	"lab_06/internal/entity"
	"lab_06/internal/repo"
	"lab_06/pkg/logging"
)

type repository struct {
	db     *sqlx.DB
	logger *logging.Logger
}

func (r *repository) CountProductModelsWithManufacturer(manufacturerName string) (count int, err error) {
	query := `
			select count(*)
			from productmodel p 
				join manufacturer m on p.manufacturer = m.id
			where m."name" = $1;
	`
	res := r.db.QueryRow(query, manufacturerName)
	err = res.Scan(&count)
	return
}

func (r *repository) FindOrdersWithProductModel(productModelName string) (orders []entity.Order, err error) {
	query := `
			select o.id, o."date"
			from orders o 
				join order_productmodel op on o.id = op.order_id
				join productmodel p on op.product_model_id = p.id
			where p."name" = $1;
	`
	rows, err := r.db.Queryx(query, productModelName)
	if err != nil {
		return
	}
	order := entity.Order{}
	for rows.Next() {
		err = rows.StructScan(&order)
		if err != nil {
			return
		}

		orders = append(orders, order)
	}
	return
}

func (r *repository) FindAvgProductModelsPrice() (mans []entity.Manufacturer, prices []float64, err error) {
	query := `with x as (
			select m."name", p.price
			from productmodel p
				join manufacturer m on p.manufacturer = m.id
			)
			select distinct "name", avg(price) over(partition by x."name")
			from x;
	`
	rows, err := r.db.Queryx(query)
	if err != nil {
		return
	}
	man := entity.Manufacturer{}
	var price float64
	for rows.Next() {
		err = rows.Scan(&man.Name, &price)
		if err != nil {
			return
		}

		mans = append(mans, man)
		prices = append(prices, price)
	}
	return
}

func (r *repository) TableColumns(table string, schema string) (tableColumns []entity.TableColumn, err error) {
	query := `
	SELECT
		column_name "name",
		data_type "type"
	FROM
		information_schema."columns" c
	WHERE
		c.table_name  =  $1 and table_schema  = $2
    `

	res, err := r.db.Queryx(query, table, schema)
	if err != nil {
		return
	}
	tableColumn := entity.TableColumn{}
	for res.Next() {
		err = res.StructScan(&tableColumn)
		if err != nil {
			return
		}

		tableColumns = append(tableColumns, tableColumn)
	}

	return
}

func (r *repository) MaxProductPrice() (maxPrice float64, err error) {
	query := `
	SELECT get_max_product_price()
	`
	maxPrice = 0

	row := r.db.QueryRow(query)
	err = row.Scan(&maxPrice)

	return
}

func (r *repository) FindCustomers(firstName string) (customers []entity.Customer, err error) {
	query := `
		SELECT *
		FROM get_customer($1)
	`
	rows, err := r.db.Queryx(query, firstName)
	if err != nil {
		return
	}

	customer := entity.Customer{}
	for rows.Next() {
		err = rows.StructScan(&customer)
		if err != nil {
			return
		}

		customers = append(customers, customer)
	}
	return
}

func (r *repository) UpdateStorageCnt(productName string, newCnt int) (err error) {
	query := `
		call change_storage_cnt($1, $2)
	`
	_, err = r.db.Exec(query, productName, newCnt)

	return
}

func (r *repository) CurrentSchema() (schema string, err error) {
	query := `
	SELECT current_schema() 
	`

	res := r.db.QueryRow(query)
	err = res.Scan(&schema)
	return
}

func (r *repository) CreateEmployeeTable() (err error) {
	query := `
	CREATE TABLE IF NOT EXISTS employee(
		id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
		name TEXT NOT NULL,
		salary int NOT NULL
	);
	`

	_, err = r.db.Exec(query)
	return err
}

func (r *repository) InsertEmployee(emp entity.Employee) (err error) {
	query := `
	INSERT INTO employee (name, salary) VALUES (:name, :salary)
	`
	_, err = r.db.NamedExec(query, emp)
	return
}

func (r *repository) DropEmployeeTable() (err error) {
	query := `
	DROP TABLE IF EXISTS employee;
	`

	_, err = r.db.Exec(query)
	return err
}

func New(db *sqlx.DB, logger *logging.Logger) repo.Repo {
	return &repository{
		db:     db,
		logger: logger,
	}

}
