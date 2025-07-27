CREATE DATABASE IF NOT EXISTS pharmacy;
USE pharmacy;

CREATE TABLE IF NOT EXISTS medicines (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    price DOUBLE,
    quantity INT
);
