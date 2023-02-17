CREATE SCHEMA IF NOT EXISTS hotel DEFAULT CHARACTER SET utf8;

use hotel;

DROP TABLE IF EXISTS account;
CREATE TABLE account(
                        id INT UNIQUE NOT NULL AUTO_INCREMENT,
                        first_name VARCHAR(255) NOT NULL,
                        last_name VARCHAR(255) NOT NULL,
                        email VARCHAR(255) NOT NULL,
                        tel VARCHAR(255),
                        password VARCHAR(255) NOT NULL,
                        role VARCHAR(255) NOT NULL,
                        PRIMARY KEY (id)
);

INSERT INTO account (id, first_name, last_name, email, tel, password, role)
VALUES
    (1, 'Stephan', 'Rockwell', 'st@gmail.com', '+193520435634', 'LightFlairs', 'ROLE_ADMIN'),
    (2, 'Andrew', 'Hetman', 'ah@gmail.com', '+380969826345', 'Tales', 'ROLE_USER'),
    (3, 'Mary', 'Perry', 'marry.star@gmail.com', '+380988476264', 'WeatherCoat', 'ROLE_USER')

