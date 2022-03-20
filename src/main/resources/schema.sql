CREATE TABLE IF NOT EXISTS user(
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(30) NOT NULL,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30),
    password VARCHAR(255) NOT NULL,
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

CREATE TABLE IF NOT EXISTS user_roles (
    id bigint primary key auto_increment,
    user_id INT NOT NULL,
    role_name varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS roles_permissions (
    id bigint primary key auto_increment,
    role_name VARCHAR(100) NOT NULL,
    permission VARCHAR(100) NOT NULL
);