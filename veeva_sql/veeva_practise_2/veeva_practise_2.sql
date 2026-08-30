use veeva;

CREATE TABLE salesman (
    salesman_id INT PRIMARY KEY,
    name VARCHAR(50),
    city VARCHAR(50),
    commission DECIMAL(4,2)
);

INSERT INTO salesman VALUES
(5001, 'James Hoog', 'New York', 0.15),
(5002, 'Nail Knite', 'Paris', 0.13),
(5005, 'Pit Alex', 'London', 0.11),
(5006, 'Mc Lyon', 'Paris', 0.14),
(5003, 'Lauson Hen', NULL, 0.12),
(5007, 'Paul Adam', 'Rome', 0.13);

CREATE TABLE customer (
    customer_id INT PRIMARY KEY,
    cust_name VARCHAR(50),
    city VARCHAR(50),
    grade INT,
    salesman_id INT,
    FOREIGN KEY (salesman_id) REFERENCES salesman(salesman_id)
);

INSERT INTO customer VALUES
(3002, 'Nick Rimando', 'New York', 100, 5001),
(3005, 'Graham Zusi', 'California', 200, 5002),
(3001, 'Brad Guzan', 'London', NULL, 5005),
(3004, 'Fabian Johns', 'Paris', 300, 5006),
(3007, 'Brad Davis', 'New York', 200, 5001),
(3009, 'Geoff Camero', 'Berlin', 100, 5003),
(3008, 'Julian Green', 'London', 300, 5002),
(3003, 'Jozy Altidor', 'Moscow', 200, 5007);

CREATE TABLE orders1 (
    ord_no INT PRIMARY KEY,
    purch_amt DECIMAL(10,2),
    ord_date DATE,
    customer_id INT,
    salesman_id INT,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (salesman_id) REFERENCES salesman(salesman_id)
);

INSERT INTO orders1 VALUES
(70001, 150.50, '2012-10-05', 3005, 5002),
(70009, 270.65, '2012-09-10', 3001, 5005),
(70002, 65.26, '2012-10-05', 3002, 5001),
(70004, 110.50, '2012-08-17', 3009, 5003),
(70007, 948.50, '2012-09-10', 3005, 5002),
(70005, 2400.60, '2012-07-27', 3007, 5001),
(70008, 5760.00, '2012-09-10', 3002, 5001),
(70010, 1983.43, '2012-10-10', 3004, 5006),
(70003, 2480.40, '2012-10-10', 3009, 5003),
(70012, 250.45, '2012-06-27', 3008, 5002),
(70011, 75.29, '2012-08-17', 3003, 5007),
(70013, 3045.60, '2012-04-25', 3002, 5001);

CREATE TABLE company_mast (
    COM_ID INT PRIMARY KEY,
    COM_NAME VARCHAR(50)
);

INSERT INTO company_mast VALUES
(11, 'Samsung'),
(12, 'iBall'),
(13, 'Epsion'),
(14, 'Zebronics'),
(15, 'Asus'),
(16, 'Frontech');

CREATE TABLE item_mast (
    PRO_ID INT PRIMARY KEY,
    PRO_NAME VARCHAR(100),
    PRO_PRICE DECIMAL(10,2),
    PRO_COM INT,
    FOREIGN KEY (PRO_COM) REFERENCES company_mast(COM_ID)
);

INSERT INTO item_mast VALUES
(101, 'Mother Board', 3200, 15),
(102, 'Key Board', 450, 16),
(103, 'ZIP drive', 250, 14),
(104, 'Speaker', 550, 16),
(105, 'Monitor', 5000, 11),
(106, 'DVD drive', 900, 12),
(107, 'CD drive', 800, 12),
(108, 'Printer', 2600, 13),
(109, 'Refill cartridge', 350, 13),
(110, 'Mouse', 250, 12);

CREATE TABLE emp_department (
    DPT_CODE INT PRIMARY KEY,
    DPT_NAME VARCHAR(50),
    DPT_ALLOTMENT DECIMAL(12,2)
);

INSERT INTO emp_department VALUES
(57, 'IT', 65000),
(63, 'Finance', 15000),
(47, 'HR', 240000),
(27, 'RD', 55000),
(89, 'QC', 75000);

CREATE TABLE emp_details (
    EMP_IDNO INT PRIMARY KEY,
    EMP_FNAME VARCHAR(50),
    EMP_LNAME VARCHAR(50),
    EMP_DEPT INT,
    FOREIGN KEY (EMP_DEPT) REFERENCES emp_department(DPT_CODE)
);

INSERT INTO emp_details VALUES
(127323, 'Michale', 'Robbin', 57),
(526689, 'Carlos', 'Snares', 63),
(843795, 'Enric', 'Dosio', 57),
(328717, 'Jhon', 'Snares', 63),
(444527, 'Joseph', 'Dosni', 47),
(659831, 'Zanifer', 'Emily', 47),
(847674, 'Kuleswar', 'Sitaraman', 57),
(748681, 'Henrey', 'Gabriel', 47),
(555935, 'Alex', 'Manuel', 57),
(539569, 'George', 'Mardy', 27),
(733843, 'Mario', 'Saule', 63),
(631548, 'Alan', 'Snappy', 27),
(839139, 'Maria', 'Foster', 57);
/*Write a query to display all the orders from the orders table issued by the salesman 'Paul Adam'.*/

select s.salesman_id,o.ord_no,s.name from salesman s inner join orders1 o on s.salesman_id = o.salesman_id
where s.salesman_id in (select salesman_id from salesman where name = 'james hoog');

/*Write a query to display all the orders for the salesman who belongs to the city London.*/

select s.salesman_id,o.ord_no,s.name from salesman s inner join orders1 o on s.salesman_id = o.salesman_id
where s.salesman_id in (select salesman_id from salesman where city = 'london');

/* Write a query to find all the orders issued against the salesman who may works for customer whose id is 3007.*/

select ord_no,ord_date,salesman_id from orders1 where salesman_id in (
	select salesman_id from customer where customer_id = 3007
);

/* 4. Write a query to display all the orders which values are greater than the average order value for 10th October 2012.*/

select ord_no,purch_amt from orders1 where purch_amt >
(select round(avg(purch_amt),2) as avg_amount from 
orders1 where ord_date = '2012-10-10'
);

/*Write a query to find all orders attributed to a salesman in New York.*/

select s.salesman_id,o.ord_no,s.name from salesman s inner join orders1 o on s.salesman_id = o.salesman_id
where s.salesman_id in (select salesman_id from salesman where city = 'new york');

/*Write a query to display the commission of all the salesmen servicing customers in Paris*/

select salesman_id,commission from salesman where salesman_id in(
	select salesman_id from customer where city='paris'
);

/*
 7. Write a query to display all the customers whose id is 2001 bellow the salesman ID of Mc Lyon.*/
 
 /*8. Write a query to count the customers with grades above New York's average. */
 
 select count(*) from customer where grade >(
 select avg(grade) as avg_grades from customer where city='new york'
 );

/*9. Write a query to display all customers with orders on October 5, 2012. */

select customer_id,cust_name from customer where customer_id in(
select customer_id from orders1 where ord_date='2012-10-05'
);

/*. Write a query to find the name and numbers of all salesmen who had more than one customer.*/

select salesman_id,name,total_salesman_customer_count from(
	select s.salesman_id,s.name,count(*) as total_salesman_customer_count from customer c inner join salesman s
	on s.salesman_id = c.salesman_id group by salesman_id
) as t where total_salesman_customer_count>1;

/*Write a queries to find all orders with order amounts which are on or above-average amounts for their customers.*/

select o1.ord_no,o1.customer_id,o1.purch_amt from orders1 o1 where o1.purch_amt>=(
select round(avg(o2.purch_amt),2) from orders1 o2 where o2.customer_id = o1.customer_id 
)order by o1.customer_id;

/*Write a query to find the sums of the amounts from the orders table, grouped by date, 
eliminating all those dates where the sum was not at least 1000.00 above the maximum order amount for that date. */

select ord_date,total_sum from(
select ord_date,sum(purch_amt) as total_sum,max(purch_amt) as max_amt from orders1 group by ord_date) as t
where total_sum >= max_amt+1000;

/*. Write a query to extract the data from the customer table if and only if one or more of the customers in the customer table are located in London.*/

select * from customer where 1<= (select count(*) as count from customer where city='london');

/*. Write a query to find the salesmen who have multiple customers. */

select s.salesman_id,s.name from salesman s inner join 
	(select c.salesman_id,count(*) as total_customers from customer c group by salesman_id
) as t on s.salesman_id=t.salesman_id where t.total_customers>=2;

/*. Write a query to find all the salesmen who worked for only one customer. */

select s.salesman_id,s.name from salesman s inner join 
	(select c.salesman_id,count(*) as total_customers from customer c group by salesman_id
) as t on s.salesman_id=t.salesman_id where t.total_customers=1;

/*. Write a query that extract the rows of all salesmen who have customers with more than one orders. */

select s.salesman_id,s.name from salesman s inner join 
	(select c.customer_id,c.salesman_id,count(*) total_orders from customer c group by c.customer_id) as t
on t.salesman_id = s.salesman_id where t.total_orders >1;

/*Write a query to find salesmen with all information who lives in the city where any of the customers lives. */

select salesman_id,name,city from salesman where city = any(select city from customer);