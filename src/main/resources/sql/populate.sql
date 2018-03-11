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
  (1, '2016-01-25', 10, 4),
  (2, '2016-02-27', 5, 1),
  (3, '2016-12-31', 1, 4),
  (4, '2017-01-01', 3, 3),
  (5, '2017-01-03', 2, 1),
  (6, '2017-01-05', 11, 1),
  (7, '2017-01-10', 11, 2),
  (8, '2017-01-15', 1, 1),
  (9, '2017-01-24', 11, 1),
  (10, '2017-01-25', 12, 1),
  (11, '2017-01-25', 11, 1),
  (12, '2017-01-25', 13, 1),
  (13, '2017-01-25', 13, 3),
  (14, '2017-01-26', 10, 1),
  (15, '2017-01-30', 14, 3),
  (16, '2017-01-31', 14, 1),
  (17, '2017-02-01', 14, 1),
  (18, '2017-02-02', 13, 3),
  (19, '2017-02-04', 12, 2),
  (20, '2017-02-08', 12, 1),
  (21, '2017-02-15', 12, 3),
  (22, '2017-02-16', 12, 1),
  (23, '2017-02-16', 12, 3),
  (24, '2017-02-22', 12, 1),
  (25, '2017-02-27', 12, 3),
  (26, '2017-02-28', 2, 1),
  (27, '2017-02-28', 2, 1),
  (28, '2017-03-01', 2, 4),
  (29, '2018-01-10', 2, 1),
  (30, '2018-02-15', 2, 2);

INSERT INTO
  orders_products (orders_id, products_id, selling_price, count)
VALUES
  (1, 13, 6, 3),
  (2, 15, 36, 1),
  (3, 14, 50, 2),
  (3, 15, 53, 2),
  (3, 16, 40, 2),
  (4, 14, 20, 2),
  (4, 15, 20, 2),
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
  (21, 16, 18, 1),
  (22, 10, 18, 3),
  (22, 15, 18, 1),
  (23, 15, 18, 1),
  (23, 9, 18, 1),
  (24, 15, 18, 1),
  (24, 1, 18, 1),
  (25, 14, 18, 1),
  (25, 15, 18, 1),
  (26, 13, 18, 1),
  (26, 12, 18, 1),
  (27, 15, 18, 1),
  (27, 13, 18, 1),
  (28, 15, 18, 1),
  (29, 15, 18, 1),
  (30, 14, 49, 3);