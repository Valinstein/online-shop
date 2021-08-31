CREATE TABLE users
(
    login    VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    salt     VARCHAR(64)  NOT NULL,
    role     VARCHAR(16)  NOT NULL
);