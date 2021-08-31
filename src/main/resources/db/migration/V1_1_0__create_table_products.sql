CREATE TABLE products
(
    id            SERIAL PRIMARY KEY,
    product_name  VARCHAR(255),
    product_price REAL,
    creation_date timestamp
);