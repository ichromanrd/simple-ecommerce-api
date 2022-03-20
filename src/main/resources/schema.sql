-- CREATE DATABASE blockchainspace;

-- USE `blockchainspace`;

CREATE TABLE IF NOT EXISTS user(
  id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(30) NOT NULL,
  first_name VARCHAR(30) NOT NULL,
  last_name VARCHAR(30),
  email VARCHAR(100) NOT NULL,
  phone VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS product(
  code VARCHAR(20) PRIMARY KEY,
  name VARCHAR(30) NOT NULL,
  category VARCHAR(50),
  publisher VARCHAR(50) NOT NULL,
  unit_of_measurement VARCHAR(50),
  quantity INT DEFAULT 0,
  price DECIMAL DEFAULT 0,
  owner INT,
  CONSTRAINT FK_USER_PRODUCT FOREIGN KEY(owner) REFERENCES user(id)
);

-- for insert
-- LOCK TABLES `clients` WRITE;
-- add statements for inserting here
-- after unlock
-- UNLOCK TABLES;