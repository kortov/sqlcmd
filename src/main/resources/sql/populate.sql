TRUNCATE TABLE orders_products CASCADE;
TRUNCATE TABLE products CASCADE;
TRUNCATE TABLE orders CASCADE;
TRUNCATE TABLE customers CASCADE;
TRUNCATE TABLE shops CASCADE;

INSERT INTO
  customers (id, name, surname, age)
VALUES
  (1, 'AlexandrVVV', 'Nevskiy', 15),
  (2, 'Namvvve', 'Surname', 16),
  (3, 'Irina', 'Ovechkina', 17),
  (4, 'VPetr', 'Petrov', 69),
  (5, 'VVIvan', 'IvanOv', 21),
  (6, 'Olha', 'Polkovnikova', 34),
  (7, 'IvanV', 'IvanoV', 35),
  (8, 'IvanvV', 'IvanOV', 36),
  (9, 'Antonina', 'Tishenko', 24),
  (10, 'John', 'McAffee', 24),
  (11, 'John', 'Smith', 33),
  (12, 'John', 'Rambo', 71),
  (13, 'Bill', 'Gates', 62),
  (14, 'Bill', 'Bones', 50);


INSERT INTO
  products (id, name, purchase_price)
VALUES
  (1, 'vproduct_name', 32.2),
  (2, 'productv_name', 92.2),
  (3, 'productv_name', 92.2),
  (4, 'vproductv_name', 92.2),
  (5, 'prodVuct_name', 22.2),
  (6, 'product_namev', 103.2),
  (7, 'vprovduct_namev', 32.2),
  (8, 'VVproduct_name', 42.2),
  (9, 'vproduct_name', 92.2),
  (10, 'product_namev', 92.2),
  (11, 'product_name', 92.2),
  (12, 'prodVVuct_name', 2.2),
  (13, 'Coca-Cola', 15.05),
  (14, 'Pepsi', 14.50),
  (15, 'Sprite', 15.00),
  (16, 'Snickers', 8.75);
;

INSERT INTO
  shops (id, name, address)
VALUES
  (1, 'Wallmart', 'Wallmart address'),
  (2, 'Auchan', 'Auchan address'),
  (3, 'Tesco', 'Tesco address'),
  (4, 'Mvideo', 'Mvideo address');

INSERT INTO
  orders (id, orders_date, customers_id, shops_id)
VALUES
  (1, '2017-01-25', 10, 1),
  (2, '2017-01-27', 5, 1),
  (3, '2017-02-25', 1, 4),
  (4, '2017-01-27', 3, 3),
  (5, '2017-01-25', 2, 1),
  (6, '2017-02-25', 11, 1),
  (7, '2017-02-25', 11, 2),
  (8, '2017-02-26', 1, 1),
  (9, '2017-01-23', 2, 1),
  (10, '2017-01-25', 12, 3),
  (11, '2017-01-12', 13, 2),
  (12, '2017-01-11', 13, 3),
  (13, '2017-02-20', 13, 1),
  (14, '2017-01-25', 14, 2),
  (15, '2017-01-31', 14, 3),
  (16, '2017-01-01', 14, 3),
  (17, '2017-02-01', 14, 1),
  (18, '2016-12-31', 13, 3),
  (19, '2017-01-25', 12, 2),
  (20, '2017-01-25', 12, 3),
  (21, '2017-02-07', 2, 1);


INSERT INTO
  orders_products (orders_id, products_id, selling_price, count)
VALUES
  (1, 13, 6, 3),
  (1, 15, 36, 1),
  (1, 14, 50, 2),
  (2, 15, 53, 2),
  (2, 16, 40, 2),
  (3, 14, 20, 2),
  (4, 14, 20, 2),
  (5, 14, 50, 2),
  (6, 13, 37, 2),
  (6, 16, 33, 2),
  (7, 13, 65, 2),
  (7, 16, 60, 2),
  (8, 15, 60, 2),
  (8, 16, 60, 2),
  (9, 15, 60, 2),
  (9, 16, 60, 2),
  (10, 13, 18, 1),
  (10, 14, 18, 2),
  (11, 15, 20, 1),
  (11, 13, 18, 3),
  (12, 15, 18, 3),
  (12, 16, 20, 4),
  (13, 15, 18, 2),
  (14, 14, 20, 4),
  (15, 15, 22, 1),
  (16, 13, 18, 3),
  (17, 15, 24, 1),
  (18, 14, 18, 2),
  (18, 15, 15, 1),
  (19, 13, 18, 1),
  (19, 15, 17, 3),
  (20, 16, 18, 3),
  (21, 15, 18, 1),
  (21, 14, 49, 3);
