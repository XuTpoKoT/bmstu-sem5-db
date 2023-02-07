package entity

import "database/sql"

type TableColumn struct {
	Name string `db:"name"`
	Type string `db:"type"`
}

type Manufacturer struct {
	Id   string `db:"id"`
	Name string `db:"name"`
}

type Customer struct {
	Id        string         `db:"id"`
	FirstName string         `db:"first_name"`
	LastName  string         `db:"last_name"`
	BirthDate string         `db:"birth_date"`
	Email     sql.NullString `db:"email"`
	Phone     sql.NullString `db:"phone"`
}

type Order struct {
	Id         string `db:"id"`
	CustomerId string `db:"customer_id"`
	Date       string `db:"date"`
	Status     string `db:"status"`
}

type ProductModel struct {
	Id           string  `db:"id"`
	CategoryId   string  `db:"category_id"`
	Name         string  `db:"name"`
	Price        float64 `db:"price"`
	StorageCnt   int     `db:"storage_cnt"`
	Color        string  `db:"color"`
	Type         string  `db:"type"`
	Manufacturer string  `db:"manufacturer"`
	BodyMaterial string  `db:"body_material"`
	NeckMaterial string  `db:"neck_material"`
	SoundPickup  string  `db:"sound_pickup"`
	CntFrets     string  `db:"cnt_frets"`
	CntKeys      string  `db:"cnt_keys"`
	CntVoices    string  `db:"cnt_voices"`
	CntStyles    string  `db:"cnt_styles"`
	CntTimbres   string  `db:"cnt_timbres"`
	Power        string  `db:"power"`
}

// ==================
type Employee struct {
	Id     string `db:"id"`
	Name   string `db:"name"`
	Salary int    `db:"salary"`
}
