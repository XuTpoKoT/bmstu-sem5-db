package main

import (
	"context"
	repo "lab_06/internal/repo/postgres"
	dbclient "lab_06/pkg/dbclient/postgres"
	"lab_06/pkg/logging"
)

func main() {
	logger := logging.Get()
	dbConfig := dbclient.Config{
		Host:     "localhost",
		Port:     "5432",
		Username: "postgres",
		Password: "postgres",
		Database: "music_shop_db",
	}

	db, err := dbclient.New(context.Background(), dbConfig)
	if err != nil {
		logger.Panic(err)
	}

	r := repo.New(db, logger)

	command := -1
	for command != 0 {
		drawMenu()
		command = getCommand()
		handleCommand(command, r)
	}
}
