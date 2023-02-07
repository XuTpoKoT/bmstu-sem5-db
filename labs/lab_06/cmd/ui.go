package main

import (
	"fmt"
	"lab_06/internal/entity"
	"lab_06/internal/repo"
	"lab_06/pkg/logging"
	"os"
)

func drawMenu() {
	fmt.Println("\nМеню:")
	fmt.Println("0. Выйти")
	fmt.Println("1. Скалярный запрос [Кол-во моделей производителя]")
	fmt.Println("2. Запрос с несколькими JOIN [Id и даты заказов определённого товара]")
	fmt.Println("3. Запрос с ОТВ и оконными функциями [Средняя цена товаров у производителей]")
	fmt.Println("4. Запрос к метаданным [Список аттрибутов и их типов у заданной таблицы]")
	fmt.Println("5. Скалярная функция [Максимальная цена товара]")
	fmt.Println("6. Табличную функция [Заказчики, подходящие по имени]")
	fmt.Println("7. Хранимая процедура [Изменить кол-во товара]")
	fmt.Println("8. Системная функция [Вывести текущую схему по умолчанию]")
	fmt.Println("9. Создать таблицу [Таблица работников магазина]")
	fmt.Println("10. Вставить данные в созданную таблицу [Добавить работника]")
	fmt.Println("11. []")
}

func getCommand() (command int) {
	fmt.Print("Input command: ")
	if _, err := fmt.Scanf("%d", &command); err != nil {
		command = -1
	}
	return
}

func input(msg, _default string) (s string) {
	fmt.Print(msg)
	if _, err := fmt.Scanf("%s", &s); err != nil {
		s = _default
	}
	return
}

func handleCommand(command int, r repo.Repo) {
	logger := logging.Get()
	switch command {
	case -1:
		fmt.Println()
	case 0:
		os.Exit(0)
	case 1:
		manufacturerName := "FENDER"
		manufacturerName = input(fmt.Sprintf("Input manufacturer name: (default %s): ", manufacturerName), manufacturerName)

		res, err := r.CountProductModelsWithManufacturer(manufacturerName)
		if err != nil {
			logger.Error(err)
			return
		}
		fmt.Printf("Count productModels = %d\n", res)
	case 2:
		productModelName := "ARIA UBL-6838"
		productModelName = input(fmt.Sprintf("Input productModelName: (default %s): ", productModelName), productModelName)

		res, err := r.FindOrdersWithProductModel(productModelName)
		if err != nil {
			logger.Error(err)
			return
		}
		for i, order := range res {
			fmt.Printf("%02d. %s %s\n", i+1, order.Id, order.Date)
		}
	case 3:
		manufacturers, prices, err := r.FindAvgProductModelsPrice()
		if err != nil {
			logger.Error(err)
			return
		}
		for i := 0; i < len(manufacturers); i++ {
			fmt.Printf("%02d. %s %f \n", i+1, manufacturers[i].Name, prices[i])
		}
	case 4:
		schema := "public"
		table := "productmodel"
		schema = input(fmt.Sprintf("Input schema: (default %s): ", schema), schema)
		table = input(fmt.Sprintf("Input table: (default %s): ", table), table)
		tableColumns, err := r.TableColumns(table, schema)
		if err != nil {
			logger.Error(err)
			return
		}
		for i, tableColumn := range tableColumns {
			fmt.Printf("%02d. column=%s type=%s\n", i+1, tableColumn.Name, tableColumn.Type)
		}
	case 5:
		maxPrice, err := r.MaxProductPrice()
		if err != nil {
			logger.Error(err)
			return
		}
		fmt.Printf("Max product price = %f\n", maxPrice)
	case 6:
		customerName := "Scott"
		customerName = input(fmt.Sprintf("Input customer name: (default %s): ", customerName), customerName)

		res, err := r.FindCustomers(customerName)
		if err != nil {
			logger.Error(err)
			return
		}
		for i, customer := range res {
			fmt.Printf("%02d. %s %s\n", i+1, customer.FirstName, customer.LastName)
		}
	case 7:
		productModelName := "CRAFTER MLR-3464"
		productModelName = input(fmt.Sprintf("Input productModelName: (default %s): ", productModelName), productModelName)

		newCnt := 10
		fmt.Printf("Input new storage count:")
		_, err := fmt.Scanf("%d", &newCnt)
		if err != nil {
			logger.Error(err)
			return
		}

		err = r.UpdateStorageCnt(productModelName, newCnt)
		if err != nil {
			logger.Error(err)
			return
		}
		fmt.Println("Successfully update storage count.")
	case 8:
		schema, err := r.CurrentSchema()
		if err != nil {
			logger.Error(err)
			return
		}
		fmt.Printf("Current schema is '%s'\n", schema)
	case 9:
		err := r.CreateEmployeeTable()
		if err != nil {
			logger.Error(err)
			return
		}
		fmt.Println("Successfully create table or it already exists.")
	case 10:
		emp := entity.Employee{
			Name:   "Tom",
			Salary: 700000,
		}
		emp.Name = input(fmt.Sprintf("Input employee name: (default %s): ", emp.Name), emp.Name)

		fmt.Printf("Input employee salary:")
		var sal int
		_, err := fmt.Scanf("%d", &sal)
		if err != nil {
			logger.Error(err)
			return
		}
		emp.Salary = sal

		err = r.InsertEmployee(emp)
		if err != nil {
			logger.Error(err)
			return
		}
		fmt.Println("Successfully insert employee.")
	case 11:
		{
			r.DropEmployeeTable()
		}
	}

}
