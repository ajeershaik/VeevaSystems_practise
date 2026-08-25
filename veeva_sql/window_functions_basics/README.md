# Veeva SQL Practice

This project contains a small MySQL database used to practice SQL queries involving tables, joins, aggregate functions, date functions, filtering, grouping, and subqueries.

## Database

```sql
CREATE DATABASE veeva;
USE veeva;
```

---

# 1. Orders Table

## Table Structure

```text
+--------------+---------------+
| Column       | Type          |
+--------------+---------------+
| id           | INT           |
| order_date   | DATE          |
| order_amount | DECIMAL(10,2) |
+--------------+---------------+
```

## Create Table

```sql
CREATE TABLE orders (
    id INT PRIMARY KEY,
    order_date DATE,
    order_amount DECIMAL(10,2)
);
```

## Data

| id | order_date | order_amount |
|---:|------------|-------------:|
| 1 | 2023-05-09 | 1250.00 |
| 2 | 2023-05-09 | 900.00 |
| 3 | 2023-05-10 | 1810.00 |
| 4 | 2023-05-11 | 650.00 |
| 5 | 2023-05-11 | 100.00 |

## Query: Total Order Amount for Each Date

```sql
SELECT
    order_date,
    SUM(order_amount) AS total_amount
FROM orders
GROUP BY order_date;
```

### Output

| order_date | total_amount |
|------------|-------------:|
| 2023-05-09 | 2150.00 |
| 2023-05-10 | 1810.00 |
| 2023-05-11 | 750.00 |

---

# 2. Customers Table

## Table Structure

```text
+---------+-------------+
| Column  | Type        |
+---------+-------------+
| cust_id | INT         |
| name    | VARCHAR(15) |
| city    | VARCHAR(15) |
+---------+-------------+
```

## Create Table

```sql
CREATE TABLE customers (
    cust_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(15),
    city VARCHAR(15)
);
```

## Data

| cust_id | name | city |
|---:|---|---|
| 1 | ajeer | tanuku |
| 2 | bittu | pothavaram |
| 3 | datta | gudem |
| 4 | dheeraj | tanuku |
| 5 | nikhil | gudem |
| 6 | sri ram | moyyuru |
| 7 | gopi | pothavaram |
| 8 | pandu | tanuku |
| 9 | ram teja | eluru |
| 10 | manikanta | gudem |
| 11 | pandu | eluru |

---

# 3. Orderings Table

## Table Structure

```text
+--------------+---------------+
| Column       | Type          |
+--------------+---------------+
| order_id     | INT           |
| cust_id      | INT           |
| order_date   | DATE          |
| order_amount | DECIMAL(10,2) |
+--------------+---------------+
```

## Create Table

```sql
CREATE TABLE orderings (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    cust_id INT,
    order_date DATE,
    order_amount DECIMAL(10,2),
    FOREIGN KEY (cust_id) REFERENCES customers(cust_id)
);
```

## Data

| order_id | cust_id | order_date | order_amount |
|---:|---:|---|---:|
| 1 | 1 | 2023-09-14 | 2110.00 |
| 2 | 1 | 2023-11-11 | 300.00 |
| 3 | 5 | 2023-12-09 | 3110.00 |
| 4 | 2 | 2023-02-04 | 2010.00 |
| 5 | 11 | 2023-11-01 | 300.00 |
| 6 | 1 | 2023-12-17 | 310.00 |

---

# 4. SQL Practice Queries

## Query 1: Extract Month from Order Date

```sql
SELECT
    DATE_FORMAT(order_date, '%m') AS month_only
FROM orderings;
```

### Output

| month_only |
|---|
| 09 |
| 11 |
| 12 |
| 02 |
| 11 |
| 12 |

---

## Query 2: Find Orders Except December

```sql
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
```

### Output

| cust_id | name | order_date | order_count |
|---:|---|---|---:|
| 1 | ajeer | 2023-09-14 | 1 |
| 1 | ajeer | 2023-11-11 | 1 |
| 2 | bittu | 2023-02-04 | 1 |
| 11 | pandu | 2023-11-01 | 1 |

---

## Query 3: Customer with Highest Total Order Amount

```sql
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
```

### Output

| cust_id | name | total_amount |
|---:|---|---:|
| 5 | nikhil | 3110.00 |

---

## Query 4: Orders Between Two Specific Dates

```sql
SELECT
    order_id,
    order_date
FROM orderings
WHERE order_date BETWEEN '2023-04-01' AND '2023-10-01';
```

### Output

| order_id | order_date |
|---:|---|
| 1 | 2023-09-14 |

---

## Query 5: Average Order Value for Each City

```sql
SELECT
    c.city,
    AVG(o.order_amount) AS avg_order_value
FROM customers c
INNER JOIN orderings o
    ON c.cust_id = o.cust_id
GROUP BY c.city;
```

### Output

| city | avg_order_value |
|---|---:|
| tanuku | 906.6667 |
| pothavaram | 2010.0000 |
| gudem | 3110.0000 |
| eluru | 300.0000 |

---

## Query 6: Customers Who Have Not Placed Any Orders

```sql
SELECT
    c.cust_id,
    c.name
FROM customers c
LEFT JOIN orderings o
    ON c.cust_id = o.cust_id
WHERE o.order_id IS NULL;
```

### Output

| cust_id | name |
|---:|---|
| 3 | datta |
| 4 | dheeraj |
| 6 | sri ram |
| 7 | gopi |
| 8 | pandu |
| 9 | ram teja |
| 10 | manikanta |

---

# Concepts Practiced

- CREATE DATABASE
- CREATE TABLE
- INSERT
- SELECT
- DESC
- Primary Key
- Foreign Key
- AUTO_INCREMENT
- INNER JOIN
- LEFT JOIN
- WHERE
- BETWEEN
- GROUP BY
- ORDER BY
- LIMIT
- SUM()
- COUNT()
- AVG()
- DATE_FORMAT()
- Aggregate Functions
- Date Filtering

## How to Run

Open MySQL and execute the SQL file:

```sql
SOURCE veeva_sql_practice.sql;
```

Or copy and execute the queries individually in MySQL Workbench.

## Files

```text
Veeva SQL Practice/
├── veeva_sql_practice.sql
└── README.md
```
