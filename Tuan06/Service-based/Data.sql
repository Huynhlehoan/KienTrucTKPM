CREATE DATABASE food_delivery_db;
USE food_delivery_db;

CREATE TABLE menu_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price DOUBLE
);

CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255),
    food_item VARCHAR(255),
    total_price DOUBLE,
    status VARCHAR(50)
);

-- Thêm dữ liệu mẫu cho Menu
INSERT INTO menu_items (name, price) VALUES ('Cơm Sườn', 35000), ('Bún Bò', 45000), ('Mì Quảng', 40000);