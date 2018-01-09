-- а) Найти имена покупателей, возраст которых от 16 до 35.
SELECT
  name,
  age
FROM customers
WHERE age >= 16 AND age <= 35;

-- б) Найти имена покупателей, фамилия которых оканчивается на 'OV'
SELECT
  name,
  surname
FROM customers
WHERE surname ILIKE '%OV';

-- в) Найти название самого дорогой по закупке товара, в имени которого присутствует буква 'V',
--    но не первая и не последняя.
SELECT
  products.name,
  MAX(products.purchase_price)
FROM (SELECT *
      FROM products p
      WHERE p.name ~* '[^Vv]+[Vv]+[^Vv]+') AS products
WHERE products.purchase_price = (SELECT MAX(purchase_price)
                                 FROM products
                                 WHERE name ~* '[^Vv]+[Vv]+[^Vv]+')
GROUP BY products.name;

-- г) Найти имена покупателей, в имени которых присутствует буква 'V' и не больше двух раз
SELECT
  name,
  surname
FROM customers
WHERE surname SIMILAR TO '([^Vv]*[Vv][^Vv]*){0,2}';

-- д) Найти имена покупателей, длина имени которых более 3 символов и 4 символ это 'O' (Латинская буква О)
--  и возраст менее 50 лет.
SELECT name
FROM customers
WHERE name ILIKE '___o%' AND age < 50;