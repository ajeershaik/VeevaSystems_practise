# SQL Subquery Practice

This repository contains MySQL practice queries based on the Salesman, Customer, Orders, Product, and Employee datasets.

## Tables

- `salesman`
- `customer`
- `orders1`
- `company_mast`
- `item_mast`
- `emp_department`
- `emp_details`

## Topics Practiced

- Subqueries and nested subqueries
- Correlated subqueries
- `IN` and `ANY`
- `COUNT()`, `AVG()`, `SUM()`, `MAX()`
- `GROUP BY` and `ORDER BY`
- Derived tables
- Inner joins
- Date filtering
- Comparing values with aggregate results
- Finding customers and salesmen based on relationships

## Queries Covered

1. Orders issued by a particular salesman.
2. Orders for salesmen belonging to a particular city.
3. Orders associated with the salesman of a particular customer.
4. Orders greater than the average order value on a specific date.
5. Orders attributed to salesmen from New York.
6. Commissions of salesmen servicing customers in Paris.
7. Customers whose grades are above the New York customer average.
8. Customers who placed orders on October 5, 2012.
9. Salesmen who have more than one customer.
10. Orders whose amounts are at or above their customer's average order amount.
11. Dates where the total order amount is at least 1000 above the maximum order amount for that date.
12. Return all customers if at least one customer is located in London.
13. Salesmen with multiple customers.
14. Salesmen who worked for only one customer.
15. Salesmen who have customers with more than one order.
16. Salesmen whose cities match a city in the customer table.

## Key Concepts

### Subquery

A query inside another query:

```sql
SELECT *
FROM customer
WHERE customer_id IN (
    SELECT customer_id
    FROM orders1
);
```

### Correlated Subquery

The inner query refers to a column from the outer query:

```sql
SELECT o1.ord_no, o1.purch_amt
FROM orders1 o1
WHERE o1.purch_amt >= (
    SELECT AVG(o2.purch_amt)
    FROM orders1 o2
    WHERE o2.customer_id = o1.customer_id
);
```

### `IN`

Used when a subquery returns multiple possible values:

```sql
WHERE city IN (
    SELECT city
    FROM customer
);
```

### `ANY`

Used to compare a value against any value returned by a subquery:

```sql
WHERE city = ANY (
    SELECT city
    FROM customer
);
```

## Database Setup

Create the tables in dependency order because foreign keys are used:

```text
salesman
   ↓
customer
   ↓
orders1

company_mast
   ↓
item_mast

emp_department
   ↓
emp_details
```

## Practice Goal

The goal of this practice set is to become comfortable reading a SQL problem and deciding whether the solution requires:

```text
JOIN
SUBQUERY
CORRELATED SUBQUERY
IN
ANY
AGGREGATE FUNCTIONS
GROUP BY
DERIVED TABLE
```

## Tools

- MySQL
- MySQL Workbench

## Status

Completed the current SQL subquery practice set.

Future practice can cover:

- `CASE`
- Window functions
- CTEs
- Recursive CTEs
- `EXISTS` / `NOT EXISTS`
- Advanced joins
- Ranking problems
- Interview-style SQL problems
