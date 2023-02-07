DROP DATABASE music_shop_db;
CREATE DATABASE music_shop_db;
\c music_shop_db;

--Справочники
CREATE TABLE IF NOT EXISTS public.Manufacturer (
	id           uuid PRIMARY KEY DEFAULT gen_random_uuid()
	, "name"     text NOT NULL
);

CREATE TABLE IF NOT EXISTS public.GuitarModelType (
	id           uuid PRIMARY KEY DEFAULT gen_random_uuid()
	, "name"     text NOT NULL
);

CREATE TABLE IF NOT EXISTS public.Material (
	id           uuid PRIMARY KEY DEFAULT gen_random_uuid()
	, "name"     text NOT NULL
);

CREATE TABLE IF NOT EXISTS public.SoundPickup (
	id           uuid PRIMARY KEY DEFAULT gen_random_uuid()
	, "name"     text NOT NULL
);

CREATE TABLE IF NOT EXISTS public.OrderStatus (
	id           uuid PRIMARY KEY DEFAULT gen_random_uuid()
	, "name"     text NOT NULL
);

CREATE TABLE IF NOT EXISTS public.Category (
	id           uuid PRIMARY KEY DEFAULT gen_random_uuid()
	, "name"     text NOT NULL
);

--Основные таблицы
CREATE TABLE IF NOT EXISTS public.Customer (
	id 	     uuid PRIMARY KEY DEFAULT gen_random_uuid()
	, first_name text NOT NULL
	, last_name  text NOT NULL
	, birth_date date NOT NULL
	, email      text
	, phone      text
);

CREATE TABLE IF NOT EXISTS public.Orders (
	id            uuid      PRIMARY KEY DEFAULT gen_random_uuid()
	, customer_id uuid      NOT NULL REFERENCES public.Customer(id) ON DELETE CASCADE
	, "date"      timestamp NOT NULL
	, status      uuid      NOT NULL REFERENCES public.OrderStatus(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.ProductModel (
	id 	               uuid PRIMARY KEY DEFAULT gen_random_uuid()
	, category_id      uuid NOT NULL REFERENCES public.Category(id) ON DELETE CASCADE
	, "name"           text NOT NULL
	, price            numeric NOT NULL CHECK (price > 0)
	, storage_cnt      int  NOT NULL DEFAULT 0
	-- гитара
	, color            text	
	, "type"           uuid REFERENCES public.GuitarModelType(id) ON DELETE CASCADE
	, manufacturer     uuid REFERENCES public.Manufacturer(id) ON DELETE CASCADE
	, body_material    uuid REFERENCES public.Material(id) ON DELETE CASCADE
	, neck_material    uuid REFERENCES public.Material(id) ON DELETE CASCADE
	, sound_pickup     uuid REFERENCES public.SoundPickup(id) ON DELETE CASCADE
	, cnt_frets        smallint CHECK (cnt_frets BETWEEN 1 AND 50)
	-- синтезатор
	, cnt_keys         smallint CHECK (cnt_keys > 0)
	, cnt_voices       smallint CHECK (cnt_voices > 0)
	, cnt_styles       smallint CHECK (cnt_styles > 0)
	, cnt_timbres      smallint CHECK (cnt_timbres > 0)
	, "power"          numeric  CHECK ("power" > 0)
);

CREATE TABLE IF NOT EXISTS public.Order_ProductModel (
	order_id           uuid NOT NULL REFERENCES public.Orders(id) ON DELETE CASCADE
	, product_model_id uuid NOT NULL REFERENCES public.ProductModel(id) ON DELETE CASCADE
	, cnt_products 	   smallint NOT NULL CHECK (cnt_products > 0)
);











