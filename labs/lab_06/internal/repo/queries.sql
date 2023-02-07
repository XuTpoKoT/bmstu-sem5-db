-- Запрос
-- Список книг, взятых читателем, с заданным id
-- SELECT с сравнением
SELECT
  lw.name
FROM
  rental_history rh
  JOIN book b ON rh.book_id = b.id
  JOIN literary_work lw ON b.literary_work_id = lw.id
WHERE
  rh.reader_id = 'a2315e91-0d6d-41ed-a103-ecc59a5bba00';

-- AvgPageCount (функция)
-- Среднее число страниц в книгах данного хранилица
SELECT
  round(avg(lw.pages))
FROM (
  SELECT
    literary_work_id
  FROM
    book
  WHERE
    storage_id = avg_page_count.storage_id) b
  JOIN literary_work lw ON b.literary_work_id = lw.id
  --- Метаданные
  -- Аттрибуты таблицы
  SELECT
    column_name,
    data_type
  FROM
    information_schema."columns" c
  WHERE
    table_schema = "schema"
    AND c.table_name = "table"
    -- Оконные функции
    -- Список книг с наибольшим числом страниц в хранилище этой книги
    SELECT
      b.id,
      lw.name,
      MAX(lw.pages) OVER (PARTITION BY s.id) max_pages
      FROM
        book b
        JOIN "storage" s ON b.storage_id = s.id
        JOIN literary_work lw ON b.literary_work_id = lw.id;

-- кол-во незданных книг у пользака
SELECT
  count(*)
FROM (
  SELECT
    book_id
  FROM
    rental_history
  WHERE
    reader_id = $1
  GROUP BY
    (operation_type,
      book_id)
  HAVING
    COUNT(*) % 2 = 1) x;

-- Подставляемая табличная функция
-- Список книг в заданном хранилище
CREATE OR REPLACE FUNCTION storage_books (storage_id uuid)
  RETURNS TABLE (
    literary_work_id uuid
  )
  AS $$
  SELECT
    id
  FROM
    book
  WHERE
    storage_id = storage_books.storage_id
$$
LANGUAGE SQL;

-- Хранимая процедура с параметрами
-- Добавить нового читателя
CREATE OR REPLACE PROCEDURE insert_reader (first_name text, last_name text, patronymic text, gender text, birthday date, passport text)
  AS $$
BEGIN
  INSERT INTO reader (first_name, last_name, patronymic, gender, birthday, passport)
    VALUES (insert_reader.first_name, insert_reader.last_name, insert_reader.patronymic, insert_reader.gender, insert_reader.birthday, insert_reader.passport);
END;
$$
LANGUAGE sql;

WITH "list" AS (
  SELECT
    operation_type "current",
    LEAD(operation_type) OVER (PARTITION BY book_id ORDER BY operation_time) "next"
  FROM rental_history
  WHERE book_id = '422b1b06-c7cb-4ea7-958e-798e0a27e6d0'
)
SELECT
  CASE WHEN EXISTS (
    SELECT
      1
    FROM
      "list"
    WHERE
      "current" = "next") THEN
    TRUE
  WHEN (
    SELECT
      "current"
    FROM
      "list"
    LIMIT 1) = 'return' THEN
    TRUE
  ELSE
    FALSE
  END is_invalid;

SELECT
  operation_type "current",
  LEAD(operation_type) OVER (PARTITION BY book_id ORDER BY operation_time) "next"
FROM
  rental_history
WHERE
  book_id = '422b1b06-c7cb-4ea7-958e-798e0a27e6d0';

SELECT
  count(*)
FROM (
  SELECT
    count(book_num) book_res,
    book_id
  FROM (
    SELECT
      CASE operation_type
      WHEN "give" 1
      WHEN "return" - 1
      END book_num,
      book_id
    FROM
      rental_history rh
    WHERE
      reader_id = "") x
  GROUP BY
    book_id) y
WHERE
  book_res <= 0;

