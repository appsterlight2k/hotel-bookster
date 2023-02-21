CREATE SCHEMA IF NOT EXISTS hotel DEFAULT CHARACTER SET utf8;

use hotel;

ALTER TABLE booking DROP FOREIGN KEY booking_ibfk_1;
ALTER TABLE booking DROP FOREIGN KEY booking_ibfk_2;
ALTER TABLE apartments_photos DROP FOREIGN KEY apartments_photos_ibfk_1;

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
                            adults_capacity INT NOT NULL,
                            children_capacity INT NOT NULL DEFAULT 0,
                            price INT NOT NULL,
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
                                   apartment_id INT NOT NULL,
                                   path VARCHAR(1000),
                                   FOREIGN KEY(apartment_id) REFERENCES apartments(id) ON DELETE CASCADE ON UPDATE CASCADE
);


DROP TABLE IF EXISTS booking;
CREATE TABLE booking (
                         id INT UNIQUE NOT NULL AUTO_INCREMENT,
                         user_id INT NOT NULL,
                         apartment_id INT NOT NULL,
                         check_in DATE NOT NULL,
                         check_out DATE NOT NULL,
                         adults_number INT NOT NULL,
                         children_number INT NOT NULL DEFAULT 0,
                         reservation_time DATETIME,
                         is_approved BOOLEAN NOT NULL DEFAULT false,
                         is_booked BOOLEAN NOT NULL DEFAULT false,
                         is_paid BOOLEAN NOT NULL DEFAULT false,
                         is_canceled BOOLEAN NOT NULL DEFAULT false,
                         FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
                         FOREIGN KEY(apartment_id) REFERENCES apartments(id) ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO users (id, first_name, last_name, email, phone_number, password, role, description)
VALUES
    (1, 'Stephan', 'Rockwell', 'st@gmail.com', '+193520435634', 'LightFlairs', 'ROLE_ADMIN', 'SuperHero'),
    (2, 'Andrew', 'Hetman', 'ah@gmail.com', '+380969826345', 'Tales', 'ROLE_USER', ''),
    (3, 'Mary', 'Perry', 'marry.star@gmail.com', '+380988476264', 'WeatherCoat', 'ROLE_USER', ''),
    (4, 'Nikola', 'Tesla', 'tesla@gmail.com', '+380953458323', 'Pasword', 'ROLE_USER', '');

INSERT INTO apartment_class (id, name, description)
VALUES
    (1, 'Single', 'A room assigned to one person. May have one or more beds.
The room size or area of Single Rooms are generally between 37 m² to 45 m².'),
    (2, 'Double', 'A room assigned to two people. May have one or more beds.
The room size or area of Double Rooms are generally between 40 m² to 45 m².'),
    (3, 'Triple', 'A room that can accommodate three persons and has been fitted with three twin beds, one double bed and one twin bed or two double beds.
The room size or area of Triple Rooms are generally between 45 m² to 65 m².'),
    (4, 'Quad', 'A room assigned to four people. May have two or more beds.
The room size or area of Quad Rooms are generally between 70 m² to 85 m².'),
    (5, 'Queen', 'A room with a queen-sized bed. May be occupied by one or more people.
The room size or area of Queen Rooms are generally between 32 m² to 50 m².'),
    (6, 'King', 'A room with a king-sized bed. May be occupied by one or more people.
The room size or area of King Rooms are generally between 32 m² to 50 m².'),
    (7, 'Twin', 'A room with two twin beds. May be occupied by one or more people.
The room size or area of Twin Rooms are generally between 32 m² to 40 m².'),
    (8, 'Hollywood Twin Room', 'A room that can accommodate two persons with two twin beds joined together by a common headboard.
Most of the budget hotels tend to provide many of these room settings which cater both couples and parties in two.
The room size or area of Hollywood Twin Rooms are generally between 32 m² to 40 m².'),
    (9, 'Double-double', 'A Room with two double (or perhaps queen) beds.
And can accommodate two to four persons with two twin, double or queen-size beds.
The room size or area of Double-double / Double Twin rooms are generally between 50 m² to 70 m².'),
    (10, 'Studio', 'A room with a studio bed- a couch which can be converted into a bed. May also have an additional bed.
The room size or area of Studio room types are generally between 25 m² to 40 m².');


INSERT INTO apartments (id, apartment_number, rooms_count, class_id, adults_capacity, children_capacity, price, description)
VALUES
    (1, '417', 2, 4, 4, 0, 150, 'With TV'),
    (2, '201', 1, 1, 1, 1, 55, 'regular room. Close to elevator'),
    (3, '205', 1, 2, 2, 2, 90, 'regular room with refrigerator; Close to elevator'),
    (4, '302', 1, 1, 1, 1, 90, '* Room is is undergoing renovation. Regular room with refrigerator; Close to elevator');

INSERT INTO booking (id, user_id, apartment_id, check_in, check_out, adults_number, children_number, reservation_time,
                     is_approved, is_booked, is_paid, is_canceled)
VALUES
    (1, 2, 2, '2023-01-02', '2023-01-04', 1, 0, null, false, false, false, false),
    (2, 2, 1, '2023-01-05', '2023-01-07', 2, 0, null, false, false, false, false),
    (3, 4, 2, '2023-01-03', '2023-01-04', 1, 0, '2023-01-01 13:19:26', false, false, false, false);

INSERT INTO apartments_photos (id, apartment_id, path)
VALUES
    (1, 1, "photos/Room-Type-Twin-Room.jpg"),
    (2, 1, "photos/Room-Type-Triple-Room.jpg"),
    (3, 2, "photos/Room-Type-studio-Room.jpg"),
    (4, 2, "photos/Room-Type-Single-Room.jpg"),
    (5, 3, "photos/Room-Type-Queen-Room.jpg"),
    (6, 3, "photos/Room-Type-quad-Room.jpg"),
    (7, 4, "photos/Room-Type-King-Room.jpg"),
    (8, 4, "photos/Room-Type-Hollywood-Twin-Room.jpg"),
    (9, 4, "photos/Room-Type-Double-Room.jpg"),
    (10, 1, "photos/Room-Type-double-double-Room.jpg");

