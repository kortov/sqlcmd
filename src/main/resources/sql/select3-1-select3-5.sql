-- а) Найти имена товаров,
--  которые купили в магазине с именем 'Wallmart' 25 января 2017 года и купил их покупатель с именем 'John';
SELECT
  products.name,
  shops.name,
  orders.orders_date,
  customers.name
FROM
  customers
  INNER JOIN orders ON orders.customers_id = customers.id
  INNER JOIN shops ON shops.id = orders.shops_id
  INNER JOIN orders_products ON orders_products.orders_id = orders.id
  INNER JOIN products ON products.id = orders_products.products_id
WHERE
  shops.name = 'Wallmart' AND orders.orders_date = '2017-01-25' AND customers.name = 'John';

-- б) Найти даты покупок товара с названием 'Coca-Cola' в магазине с именем 'Wallmart';
SELECT
  products.name,
  shops.name,
  orders.orders_date
FROM
  shops
  INNER JOIN orders ON shops.id = orders.shops_id
  INNER JOIN orders_products ON orders_products.orders_id = orders.id
  INNER JOIN products ON products.id = orders_products.products_id
WHERE
  products.name = 'Coca-Cola' AND shops.name = 'Wallmart';

-- в) Найти общее количество покупок товара с названием 'Sprite' в январе 2017 покупателем c именем 'Bill';
SELECT
  products.name  AS product_name,
  customers.name AS customer_name,
  sum(orders_products.count)
FROM
  customers
  INNER JOIN orders ON orders.customers_id = customers.id
  INNER JOIN orders_products ON orders_products.orders_id = orders.id
  INNER JOIN products ON products.id = orders_products.products_id
WHERE
  products.name = 'Sprite' AND orders.orders_date BETWEEN '2017-01-01' AND '2017-01-31'
  AND customers.name = 'Bill'
GROUP BY products.name, customers.name;

-- г) Найти какого товара купили больше всего в магазине 'Wallmart' в феврале 2017;
SELECT max(max)
FROM (SELECT max(sum)
      FROM (SELECT
              sum(orders_products.count) AS sum,
              products.name
            FROM
              shops
              INNER JOIN orders ON shops.id = orders.shops_id
              INNER JOIN orders_products ON orders_products.orders_id = orders.id
              INNER JOIN products ON products.id = orders_products.products_id
            WHERE
              shops.name = 'Wallmart' AND orders.orders_date BETWEEN '2017-02-01' AND '2017-02-28'
            GROUP BY products.name) AS sum2) AS max;

-- д) Найти имена покупателей и сумму покупок, которые потратили
-- в феврале 2017 больше всего денег в магазине 'Tesco'.
