insert into product(id, name, price) values (101, 'fanta', 16.0);
insert into product(id, name, price) values (102, 'Coca-Cola', 13.5);
insert into product(id, name, price) values (103, 'Juice', 17.9);
insert into product(id, name, price) values (104, 'Sprite', 13.9);
insert into product(id, name, price) values (105, 'Saft', 31.0);

insert into app_user(id, first_name, last_name, email) values (101, 'Patrik', 'Svensson', 'patrik_svensson@email.com');
insert into app_user(id, first_name, last_name, email) values (102, 'Martin', 'Eriksson', 'martin_eriksson@email.com');
insert into app_user(id, first_name, last_name, email) values (103, 'Erik', 'Larsson', 'erik_larsson@email.com');
insert into app_user(id, first_name, last_name, email) values (104, 'Johanna', 'Svensson', 'johanna_svensson@email.com');
insert into app_user(id, first_name, last_name, email) values (105, 'Erik', 'Bengtsson', 'erik_bengtsson@email.com');
insert into app_user(id, first_name, last_name, email) values (106, 'Magnus', 'Ljung', 'magnus_ljung@email.com');

insert into product_order(id, order_date_time, app_user_id) values (101, '2019-01-01', 101);
insert into product_order(id, order_date_time, app_user_id) values (102, '2019-01-02', 104);
insert into product_order(id, order_date_time, app_user_id) values (103, '2019-01-03', 102);
insert into product_order(id, order_date_time, app_user_id) values (104, '2019-01-04', 101);
insert into product_order(id, order_date_time, app_user_id) values (105, '2019-01-01', 104);


insert into order_item(id, quantity, product_id, product_order_id) values(101, 2, 101, 101);
insert into order_item(id, quantity, product_id, product_order_id) values(102, 3, 102, 101);
insert into order_item(id, quantity, product_id, product_order_id) values(103, 5, 103, 102);
insert into order_item(id, quantity, product_id, product_order_id) values(104, 2, 104, 102);
insert into order_item(id, quantity, product_id, product_order_id) values(105, 6, 105, 103);
insert into order_item(id, quantity, product_id, product_order_id) values(106, 1, 101, 103);
insert into order_item(id, quantity, product_id, product_order_id) values(107, 2, 102, 103);
insert into order_item(id, quantity, product_id, product_order_id) values(108, 3, 103, 103);



