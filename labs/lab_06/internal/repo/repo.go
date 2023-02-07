package repo

import "lab_06/internal/entity"

type Repo interface {
	CountProductModelsWithManufacturer(manufacturerName string) (count int, err error) // Скалярный запрос
	FindOrdersWithProductModel(productModelName string) (orders []entity.Order, err error)
	FindAvgProductModelsPrice() (pms []entity.Manufacturer, prices []float64, err error)
	TableColumns(table string, schema string) (tableColumns []entity.TableColumn, err error)
	MaxProductPrice() (maxPrice float64, err error)
	FindCustomers(firstName string) (customers []entity.Customer, err error)
	UpdateStorageCnt(productName string, newCnt int) (err error)
	CurrentSchema() (schema string, err error)
	CreateEmployeeTable() (err error)
	InsertEmployee(emp entity.Employee) (err error)
	DropEmployeeTable() (err error)

	//FindProductModelsWithManufacturer(manufacturerName string) (pms []entity.ProductModel, err error)  // Скалярный запрос ()
}
