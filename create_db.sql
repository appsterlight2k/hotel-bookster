CREATE SCHEMA IF NOT EXISTS hotel DEFAULT CHARACTER SET utf8;

use hotel;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
                       id INT UNIQUE NOT NULL AUTO_INCREMENT,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       phone_number VARCHAR(255),
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(255) NOT NULL,
                       description VARCHAR(500),
                       PRIMARY KEY (id)
);

DROP TABLE IF EXISTS apartments;
CREATE TABLE apartments (
                            id INT UNIQUE NOT NULL AUTO_INCREMENT,
                            apartment_number VARCHAR(255) UNIQUE NOT NULL,
                            rooms_count INT NOT NULL,
                            class_id INT NOT NULL,
                            capacity INT NOT NULL,
                            price INT NOT NULL,
                            photo_id INT,
                            description VARCHAR(500),
                            PRIMARY KEY (id)
);

DROP TABLE IF EXISTS apartment_class;
CREATE TABLE apartment_class (
                                 id INT UNIQUE NOT NULL AUTO_INCREMENT,
                                 name VARCHAR(255) UNIQUE NOT NULL,
                                 description VARCHAR(500)
);

DROP TABLE IF EXISTS apartments_photos;
CREATE TABLE apartments_photos (
                                   id INT UNIQUE NOT NULL AUTO_INCREMENT,
                                   path VARCHAR(1000)
);

DROP TABLE IF EXISTS booking;
CREATE TABLE booking (
                         id INT UNIQUE NOT NULL AUTO_INCREMENT,
                         account_id INT NOT NULL,
                         apartment_id INT NOT NULL,
                         check_in DATE NOT NULL,
                         check_out DATE NOT NULL,
                         persons INT NOT NULL,
                         reservation_time TIMESTAMP NOT NULL,
                         is_approved BOOLEAN NOT NULL DEFAULT false,
                         is_booked BOOLEAN NOT NULL DEFAULT false,
                         is_paid BOOLEAN NOT NULL DEFAULT false,
                         is_canceled BOOLEAN NOT NULL DEFAULT false
);

INSERT INTO users (id, first_name, last_name, email, phone_number, password, role, description)
VALUES
    (1, 'Stephan', 'Rockwell', 'st@gmail.com', '+193520435634', 'LightFlairs', 'ROLE_ADMIN', 'SuperHero'),
    (2, 'Andrew', 'Hetman', 'ah@gmail.com', '+380969826345', 'Tales', 'ROLE_USER', ''),
    (3, 'Mary', 'Perry', 'marry.star@gmail.com', '+380988476264', 'WeatherCoat', 'ROLE_USER', '');


