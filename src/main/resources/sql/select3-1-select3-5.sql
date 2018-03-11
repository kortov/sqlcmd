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
WITH sumOfProductOrders AS (SELECT
                              sum(orders_products.count) AS sumOfCount,
                              products.name              AS products
                            FROM
                              shops
                              INNER JOIN orders ON shops.id = orders.shops_id
                              INNER JOIN orders_products ON orders_products.orders_id = orders.id
                              INNER JOIN products ON products.id = orders_products.products_id
                            WHERE
                              shops.name = 'Wallmart' AND
                              orders.orders_date BETWEEN '2017-02-01' AND '2017-02-28'
                            GROUP BY products.name)
SELECT
  products,
  sumOfCount
FROM sumOfProductOrders
GROUP BY products, sumOfCount
HAVING sumOfCount = (SELECT max(sumOfCount)
                     FROM sumOfProductOrders AS maxSums);

-- д) Найти имена покупателей и сумму покупок, которые потратили
-- в феврале 2017 больше всего денег в магазине 'Tesco'.
WITH sumsOfOrders AS (SELECT
                        customers.name                   AS customerName,
                        sum(op.count * op.selling_price) AS ordersSum
                      FROM customers
                        INNER JOIN orders o ON customers.id = o.customers_id
                        INNER JOIN shops s ON o.shops_id = s.id
                        INNER JOIN orders_products op ON o.id = op.orders_id
                      WHERE s.name = 'Tesco'
                            AND o.orders_date BETWEEN '2017-02-01' AND '2017-02-28'
                      GROUP BY customers.name, customers.surname)
SELECT
  customerName,
  ordersSum
FROM sumsOfOrders
GROUP BY customerName, ordersSum
HAVING ordersSum = (SELECT max(ordersSum)
                    FROM sumsOfOrders AS maxSums);