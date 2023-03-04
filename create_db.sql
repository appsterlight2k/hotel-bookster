CREATE SCHEMA IF NOT EXISTS hotel DEFAULT CHARACTER SET utf8;

use hotel;


ALTER TABLE booking DROP FOREIGN KEY booking_ibfk_1;
ALTER TABLE booking DROP FOREIGN KEY booking_ibfk_2;
ALTER TABLE apartments_photos DROP FOREIGN KEY apartments_photos_ibfk_1;
ALTER TABLE apartments_tags DROP FOREIGN KEY apartments_tags_ibfk_1;
ALTER TABLE apartments_tags DROP FOREIGN KEY apartments_tags_ibfk_2;

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
                            main_photo_url VARCHAR(500),
                            price INT NOT NULL,
                            description VARCHAR(500),
                            PRIMARY KEY (id)
);

DROP TABLE IF EXISTS tags;
CREATE TABLE tags (
                      id INT UNIQUE NOT NULL AUTO_INCREMENT,
                      name VARCHAR(200) NOT NULL,
                      description VARCHAR(200) NOT NULL
);

DROP TABLE IF EXISTS apartments_tags;
CREATE TABLE apartments_tags (
                                 id INT UNIQUE NOT NULL AUTO_INCREMENT,
                                 tag_id INT NOT NULL,
                                 apartment_id INT NOT NULL,
                                 FOREIGN KEY(tag_id) REFERENCES tags(id) ON DELETE CASCADE ON UPDATE CASCADE,
                                 FOREIGN KEY(apartment_id) REFERENCES apartments(id) ON DELETE CASCADE ON UPDATE CASCADE
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
                         apartment_id INT, #null if user didn't choose any apartments
    check_in DATE NOT NULL,
    check_out DATE NOT NULL,
    adults_number INT NOT NULL,
    children_number INT NOT NULL DEFAULT 0,
    reservation_time DATETIME NOT NULL,
    is_approved BOOLEAN NOT NULL DEFAULT false,
    is_booked BOOLEAN NOT NULL DEFAULT false,
    is_paid BOOLEAN NOT NULL DEFAULT false,
    is_canceled BOOLEAN NOT NULL DEFAULT false,
    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(apartment_id) REFERENCES apartments(id) ON DELETE CASCADE ON UPDATE CASCADE
);


#-----------------------------[ INSERT INITIAL DATA ] -------------------------------
INSERT INTO users (id, first_name, last_name, email, phone_number, password, role, description)
VALUES
    (1, 'Stephan', 'Rockwell', 'st@gmail.com', '+193520435634', '123', 'ROLE_MANAGER', 'SuperHero'),
    (2, 'Andrew', 'Hetman', 'ah@gmail.com', '+380969826345', 'Tales', 'ROLE_USER', ''),
    (3, 'Mary', 'Perry', 'marry.star@gmail.com', '+380988476264', 'WeatherCoat', 'ROLE_USER', ''),
	(4, 'Nikola', 'Tesla', 'tesla@gmail.com', '+380953458323', 'Pasword', 'ROLE_USER', ''),
	(5, 'Steve', 'Harrison', 'orguniversityua@epam.com', '+13093452842', '123', 'ROLE_USER', '');
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


INSERT INTO apartments (id, apartment_number, rooms_count, class_id, adults_capacity, children_capacity, main_photo_url, price, description)
VALUES
(1, '117', 2, 4, 4, 0, 'photos/Room-Type-Twin-Room.jpg', 150,  'With TV'),
(2, '201', 1, 1, 1, 1, 'photos/Room-Type-quad-Room.jpg', 100, 'regular room. Close to elevator'),
(3, '205', 1, 2, 2, 2, 'photos/Room-Type-Queen-Room.jpg', 90, 'regular room with refrigerator; Close to elevator'),
(4, '302', 1, 1, 4, 1, 'photos/Room-Type-studio-Room.jpg', 90, '* Room is is undergoing renovation. Regular room with refrigerator; Close to elevator'),

(5, '311', 1, 1, 2, 1, 'photos/Room-Type-quad-Room.jpg', 100, 'regular room. Close to elevator'),
(6, '313', 1, 1, 2, 1, 'photos/Room-Type-quad-Room.jpg', 120, 'regular room. Close to elevator'),
(7, '314', 1, 1, 2, 1, 'photos/Room-Type-quad-Room.jpg', 120, 'regular room. Close to elevator'),
(8, '402', 1, 1, 3, 1, 'photos/Room-Type-quad-Room.jpg', 120, 'regular room. Close to elevator'),
(9, '405', 1, 1, 1, 1, 'photos/Room-Type-quad-Room.jpg', 150, 'regular room. Close to elevator'),
(10, '410', 1, 1, 4, 1, 'photos/Room-Type-quad-Room.jpg', 150, 'regular room. Close to elevator');

INSERT INTO booking (id, user_id, apartment_id, check_in, check_out, adults_number, children_number, reservation_time,
					 is_approved, is_booked, is_paid, is_canceled)
VALUES
(1, 2, 1, '2023-03-12', '2023-03-14', 1, 0, '2023-03-01 13:19:26', true, false, false, false),
(2, 4, 1, '2023-03-17', '2023-03-18', 1, 0, '2023-03-03 13:19:26', true, false, false, false),
(3, 4, 1, '2023-03-21', '2023-03-21', 1, 0, '2023-03-03 13:19:26', true, false, false, false),

(4, 2, 2, '2023-03-13', '2023-03-16', 2, 0, '2023-03-05 13:19:26', true, true, false, false),
(5, 3, 2, '2023-03-18', '2023-03-20', 2, 0, '2023-03-01 13:19:26', true, true, false, false),

(6, 4, 3, '2023-03-12', '2023-03-12', 2, 0, '2023-03-02 13:19:26', true, true, true, false),
(7, 2, 3, '2023-03-15', '2023-03-15', 2, 0, '2023-03-08 13:19:26', true, true, true, false),
(8, 2, 3, '2023-03-18', '2023-03-19', 2, 0, '2023-03-08 13:19:26', true, true, true, false),
(9, 2, 3, '2023-03-21', '2023-03-21', 2, 0, '2023-03-08 13:19:26', true, true, true, false),

(10, 2, 4, '2023-03-11', '2023-03-13', 2, 0, '2023-02-11 13:19:26', true, true, true, true),
(11, 3, 4, '2023-03-18', '2023-03-21', 1, 0, '2023-02-11 13:19:26', false, false, false, false),

(12, 2, 5, '2023-03-14', '2023-03-15', 2, 0, '2023-02-11 13:19:26', false, true, true, true),
(13, 3, 5, '2023-03-18', '2023-03-18', 1, 0, '2023-02-11 13:19:26', false, false, false, false),
(14, 3, 5, '2023-03-20', '2023-03-21', 1, 0, '2023-02-11 13:19:26', false, false, false, false),

(15, 2, 6, '2023-03-15', '2023-03-15', 2, 0, '2023-02-11 13:19:26', true, true, true, true),
(16, 3, 6, '2023-03-18', '2023-03-18', 1, 0, '2023-02-11 13:19:26', false, false, false, false),
(17, 3, 6, '2023-03-20', '2023-03-21', 1, 0, '2023-02-11 13:19:26', false, false, false, false),

(18, 3, 7, '2023-03-12', '2023-03-13', 1, 0, '2023-02-11 13:19:26', false, false, false, false),
(19, 3, 7, '2023-03-17', '2023-03-18', 1, 0, '2023-02-11 13:19:26', false, false, false, false),
(20, 3, 7, '2023-03-21', '2023-03-21', 1, 0, '2023-02-11 13:19:26', false, false, false, false),

(21, 3, 8, '2023-03-13', '2023-03-15', 1, 0, '2023-02-11 13:19:26', false, false, false, false),
(22, 3, 8, '2023-03-18', '2023-03-21', 1, 0, '2023-02-11 13:19:26', false, false, false, false),
(23, 1, null, '2023-03-25', '2023-03-27', 1, 0, '2023-02-11 13:19:26', false, false, false, false),
(24, 1, 9, '2023-03-04', '2023-03-04', 1, 0, '2023-02-11 13:19:26', false, false, false, false),
(25, 1, 6, '2023-03-04', '2023-03-04', 1, 0, '2023-02-11 13:19:26', false, false, false, false),
(26, 1, 9, '2023-03-18', '2023-03-18', 1, 0, '2023-02-11 13:19:26', false, false, false, false),
(27, 1, 10, '2023-03-18', '2023-03-18', 1, 0, '2023-02-11 13:19:26', false, false, false, false);

INSERT INTO tags (id, name, description)
VALUES
(1, 'Toiletries', ''),
(2, 'Bathrobe', ''),
(3, 'Safe', ''),
(4, 'Toilet', ''),
(5, 'Sofa', ''),
(6, 'Bath or shower', ''),
(7, 'Towels', ''),
(8, 'Linen', ''),
(9, 'Socket near the bed', ''),
(10, 'Cleaning products', ''),
(11, 'Safe for allergy sufferers', ''),
(12, 'Work desk', ''),
(13, 'Living room / seating area', ''),
(14, 'TV set', ''),
(15, 'Slippers', ''),
(16, 'Refrigerator', ''),
(17, 'Telephone', ''),
(18, 'Accessories for ironing clothes', ''),
(19, 'Satellite channels', ''),
(20, 'Kettle / coffee maker', ''),
(21, 'Iron', ''),
(22, 'Heating', ''),
(23, 'Hair dryer', ''),
(24, 'Towels / bed linen for an additional fee', ''),
(25, 'Carpeted flooring', ''),
(26, 'Electric kettle', ''),
(27, 'Cable channels', ''),
(28, 'Wake-up call service', ''),
(29, 'Wardrobe or wardrobe', ''),
(30, 'The higher floors can be reached by elevator', ''),
(31, 'A hanger for clothes', ''),
(32, 'Toilet paper', ''),
(33, 'Sofa bed', ''),
(34, 'Individual air conditioning in guest rooms', '');


INSERT INTO apartments_tags (id, tag_id, apartment_id)
VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 6, 1),

(5, 1, 2),
(6, 3, 2),
(7, 4, 2),

(8, 1, 3),
(9, 3, 3),
(10, 1, 4),
(11, 1, 5);

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
