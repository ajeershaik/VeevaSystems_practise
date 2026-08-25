CREATE DATABASE IF NOT EXISTS veeva;
USE veeva;

-- ============================================
-- 1. ORDERS TABLE
-- ============================================

CREATE TABLE orders (
    id INT PRIMARY KEY,
    order_date DATE,
    order_amount DECIMAL(10,2)
);

INSERT INTO orders VALUES
(1, '2023-05-09', 1250),
(2, '2023-05-09', 900),
(3, '2023-05-10', 1810),
(4, '2023-05-11', 650),
(5, '2023-05-11', 100);

-- Display orders
SELECT * FROM orders;

-- Describe orders table
DESC orders;

-- Total order amount for each date
SELECT
    order_date,
    SUM(order_amount) AS total_amount
FROM orders
GROUP BY order_date;


-- ============================================
-- 2. CUSTOMERS TABLE
-- ============================================

CREATE TABLE customers (
    cust_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(15),
    city VARCHAR(15)
);

INSERT INTO customers (name, city) VALUES
('ajeer', 'tanuku'),
('bittu', 'pothavaram'),
('datta', 'gudem'),
('dheeraj', 'tanuku'),
('nikhil', 'gudem'),
('sri ram', 'moyyuru'),
('gopi', 'pothavaram'),
('pandu', 'tanuku'),
('ram teja', 'eluru'),
('manikanta', 'gudem'),
('pandu', 'eluru');

-- Display customers
SELECT * FROM customers;


-- ============================================
-- 3. ORDERINGS TABLE
-- ============================================

CREATE TABLE orderings (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    cust_id INT,
    order_date DATE,
    order_amount DECIMAL(10,2),
    FOREIGN KEY (cust_id) REFERENCES customers(cust_id)
);

INSERT INTO orderings (cust_id, order_date, order_amount)
VALUES
(1, '2023-09-14', 2110),
(1, '2023-11-11', 300),
(5, '2023-12-09', 3110),
(2, '2023-02-04', 2010),
(11, '2023-11-01', 300),
(1, '2023-12-17', 310);

-- Display orderings
SELECT * FROM orderings;

-- Describe orderings table
DESC orderings;


-- ============================================
-- 4. SQL PRACTICE QUERIES
-- ============================================

-- Extract month from order date
SELECT
    DATE_FORMAT(order_date, '%m') AS month_only
FROM orderings;


-- Find orders except December
SELECT
    c.cust_id,
    c.name,
    o.order_date,
    COUNT(c.cust_id) AS order_count
FROM customers c
INNER JOIN orderings o
    ON c.cust_id = o.cust_id
WHERE DATE_FORMAT(o.order_date, '%m') != '12'
GROUP BY c.cust_id, c.name, o.order_date
ORDER BY order_count DESC;


-- Customer with the highest total order amount
SELECT
    c.cust_id,
    c.name,
    SUM(o.order_amount) AS total_amount
FROM customers c
INNER JOIN orderings o
    ON c.cust_id = o.cust_id
GROUP BY c.cust_id, c.name
ORDER BY total_amount DESC
LIMIT 1;


-- Orders placed between two specific dates
SELECT
    order_id,
    order_date
FROM orderings
WHERE order_date BETWEEN '2023-04-01' AND '2023-10-01';


-- Average order value for each city
SELECT
    c.city,
    AVG(o.order_amount) AS avg_order_value
FROM customers c
INNER JOIN orderings o
    ON c.cust_id = o.cust_id
GROUP BY c.city;


-- Customers who have not placed any orders
SELECT
    c.cust_id,
    c.name
FROM customers c
LEFT JOIN orderings o
    ON c.cust_id = o.cust_id
WHERE o.order_id IS NULL;