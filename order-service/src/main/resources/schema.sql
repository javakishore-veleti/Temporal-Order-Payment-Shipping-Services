drop table if exists order_db.products;
create table order_db.products
(
    product_id    varchar(50) primary key,
    product_code varchar(100),
    name  varchar(255),
    price decimal(10, 2)
);
insert into order_db.products (product_id, product_code, name, price)
values ('1', 'iPhone', 'iPhone', 700.00);
insert into order_db.products (product_id,product_code, name, price)
values ('2', 'Samsung Z Fold', 'Samsung Z Fold', 500.00);