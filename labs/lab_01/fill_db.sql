COPY Category(id, name)
FROM '/mnt/lab_01/data/categories.csv' DELIMITER ',' CSV HEADER;

COPY GuitarModelType(id, name)
FROM '/mnt/lab_01/data/guitar_types.csv' DELIMITER ',' CSV HEADER;

COPY Manufacturer(id, name)
FROM '/mnt/lab_01/data/manufacturers.csv' DELIMITER ',' CSV HEADER;

COPY Material(id, name)
FROM '/mnt/lab_01/data/materials.csv' DELIMITER ',' CSV HEADER;

COPY SoundPickup(id, name)
FROM '/mnt/lab_01/data/sound_pickups.csv' DELIMITER ',' CSV HEADER;

COPY OrderStatus(id, name)
FROM '/mnt/lab_01/data/order_statuses.csv' DELIMITER ',' CSV HEADER;

COPY Customer(id, first_name, last_name, birth_date, email, phone)
FROM '/mnt/lab_01/data/customers.csv' DELIMITER ',' CSV HEADER;

COPY ProductModel(id, category_id, name, price, storage_cnt, color, type, manufacturer,
                      body_material, neck_material, sound_pickup,
                      cnt_frets, cnt_keys, cnt_voices, cnt_styles, cnt_timbres, power)
FROM '/mnt/lab_01/data/ProductModel.csv' DELIMITER ',' CSV HEADER;

COPY Orders(id, customer_id, "date", status)
FROM '/mnt/lab_01/data/Orders.csv' DELIMITER ',' CSV HEADER;

COPY Order_ProductModel(order_id, product_model_id, cnt_products)
FROM '/mnt/lab_01/data/Order_ProductModel.csv' DELIMITER ',' CSV HEADER;


