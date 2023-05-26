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
                       description VARCHAR(5000),
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
                            description VARCHAR(5000),
                            is_unavailable BOOLEAN DEFAULT false,
                            PRIMARY KEY (id)
);

DROP TABLE IF EXISTS offered_apartments;
CREATE TABLE offered_apartments (
                                    id INT UNIQUE NOT NULL AUTO_INCREMENT,
                                    booking_id INT NOT NULL,
                                    apartment_id INT NOT NULL,
                                    message VARCHAR(5000)
);

DROP TABLE IF EXISTS tags;
CREATE TABLE tags (
                      id INT UNIQUE NOT NULL AUTO_INCREMENT,
                      name VARCHAR(255) NOT NULL,
                      is_basic BOOLEAN NOT NULL DEFAULT false,
                      description VARCHAR(5000) NOT NULL
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
                                 description VARCHAR(5000)
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
                         apartment_id INT, #null if user didn't choose any apartments (i.e. Request for Booking)
    request_class_id INT,
    check_in DATE NOT NULL,
    check_out DATE NOT NULL,
    adults_number INT NOT NULL,
    children_number INT NOT NULL DEFAULT 0,
    reservation_time DATETIME NOT NULL,
	comments VARCHAR(5000),
    is_offered BOOLEAN NOT NULL DEFAULT false,
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
    (1, 'Stephan', 'Rockwell', 'manager@gmail.com', '+193520435634', '123', 'ROLE_MANAGER', 'main manager'),
    (2, 'Andrew', 'Hetman', 'ah@gmail.com', '+380969826345', 'Tales', 'ROLE_USER', ''),
    (3, 'Mary', 'Perry', 'marry.star@gmail.com', '+380988476264', 'WeatherCoat', 'ROLE_USER', ''),
	(4, 'Nikola', 'Tesla', 'tesla@gmail.com', '+380953458323', 'Pasword', 'ROLE_USER', ''),
	(5, 'Steve', 'Harrison', 'orguniversityua@epam.com', '+13093452842', '123', 'ROLE_USER', ''),
	(6, 'Tester', 'Tester', 'tester@gmail.com', '+100000000', '123', 'ROLE_USER', 'tester');


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


INSERT INTO apartments (id, apartment_number, rooms_count, class_id, adults_capacity, children_capacity, main_photo_url, price, description, is_unavailable)
VALUES
(1, '117', 2, 4, 4, 0, 'photos/Room-Type-Twin-Room.jpg', 150,  'With TV', false),
(2, '201', 1, 1, 1, 1, 'photos/Room-Type-quad-Room.jpg', 100, 'regular room. Close to elevator', false),
(3, '205', 1, 2, 2, 2, 'photos/Room-Type-Queen-Room.jpg', 90, 'regular room with refrigerator; Close to elevator', false),
(4, '302', 1, 1, 4, 1, 'photos/Room-Type-studio-Room.jpg', 90, '* Room is is undergoing renovation. Regular room with refrigerator; Close to elevator', false),
(5, '311', 1, 1, 2, 1, 'photos/Room-Type-quad-Room.jpg', 100, 'regular room. Close to elevator', false),
(6, '313', 1, 1, 2, 1, 'photos/Room-Type-quad-Room.jpg', 120, 'regular room. Close to elevator', false),
(7, '314', 1, 1, 2, 1, 'photos/Room-Type-quad-Room.jpg', 120, 'regular room. Close to elevator', false),
(8, '402', 1, 1, 3, 1, 'photos/Room-Type-quad-Room.jpg', 120, 'regular room. Close to elevator', false),
(9, '405', 1, 1, 1, 1, 'photos/Room-Type-quad-Room.jpg', 150, 'regular room. Close to elevator', false),
(10, '410', 1, 1, 4, 1, 'photos/Room-Type-quad-Room.jpg', 150, 'regular room. Close to elevator', true),
(11, '411', 1, 4, 4, 4, 'photos/Room-Type-quad-Room.jpg', 150, 'luxary room. Close to elevator', false),
(12, '412', 1, 4, 4, 4, 'photos/Room-Type-quad-Room.jpg', 150, 'luxary room. Close to elevator', false),
(13, '413', 1, 4, 4, 4, 'photos/Room-Type-quad-Room.jpg', 150, 'luxary room. Close to elevator', false),
(14, '414', 1, 4, 4, 4, 'photos/Room-Type-quad-Room.jpg', 150, 'luxary room. Close to elevator', false),
(15, '415', 1, 4, 4, 4, 'photos/Room-Type-quad-Room.jpg', 150, 'luxary room. Close to elevator', false),
(16, '416', 1, 4, 4, 4, 'photos/Room-Type-quad-Room.jpg', 150, 'luxary room. Close to elevator', false),
(17, '417', 1, 4, 4, 4, 'photos/Room-Type-quad-Room.jpg', 160, 'luxary room. Close to elevator', false),
(18, '418', 1, 4, 4, 4, 'photos/Room-Type-King-Room.jpg', 150, 'luxary room. Close to elevator', false),
(19, '419', 1, 4, 3, 4, 'photos/Room-Type-Twin-Room.jpg', 160, 'luxary room. Close to elevator', false),
(20, '420', 1, 4, 4, 4, 'photos/Room-Type-studio-Room.jpg', 150, 'luxary room. Close to elevator', true),
(21, '421', 1, 4, 3, 4, 'photos/Room-Type-quad-Room.jpg', 180, 'luxary room. Close to elevator', false),
(22, '422', 1, 4, 4, 4, 'photos/Room-Type-quad-Room.jpg', 120, 'luxary room. Close to elevator', false),
(23, '423', 1, 4, 4, 4, 'photos/Room-Type-quad-Room.jpg', 150, 'luxary room. Close to elevator', false),
(24, '424', 1, 4, 4, 4, 'photos/Room-Type-quad-Room.jpg', 180, 'luxary room. Close to elevator', false),
(25, '425', 1, 4, 4, 4, 'photos/Room-Type-quad-Room.jpg', 120, 'luxary room. Close to elevator', false),
(26, '426', 1, 4, 4, 4, 'photos/Room-Type-quad-Room.jpg', 190, 'luxary room. Close to elevator', false),
(27, '427', 1, 4, 4, 4, 'photos/Room-Type-quad-Room.jpg', 150, 'luxary room. Close to elevator', false),
(28, '428', 1, 4, 4, 4, 'photos/Room-Type-quad-Room.jpg', 130, 'luxary room. Close to elevator', false),
(29, '429', 1, 4, 4, 4, 'photos/Room-Type-quad-Room.jpg', 150, 'luxary room. Close to elevator', false),
(30, '430', 1, 4, 4, 4, 'photos/Room-Type-quad-Room.jpg', 140, 'luxary room. Close to elevator', true),
(31, '431', 1, 4, 4, 4, 'photos/Room-Type-quad-Room.jpg', 180, 'luxary room. Close to elevator', false),
(32, '432', 1, 4, 4, 4, 'photos/Room-Type-quad-Room.jpg', 120, 'luxary room. Close to elevator', false),
(33, '501', 1, 1, 1, 1, 'photos/Room-Type-quad-Room.jpg', 100, 'regular room. Close to elevator', false),
(34, '502', 1, 1, 1, 1, 'photos/Room-Type-quad-Room.jpg', 100, 'regular room. Close to elevator', false),
(35, '503', 1, 1, 1, 1, 'photos/Room-Type-quad-Room.jpg', 100, 'regular room. Close to elevator', false);

INSERT INTO booking (id, user_id, apartment_id, request_class_id, check_in, check_out, adults_number, children_number, reservation_time, comments,
					 is_offered, is_approved, is_booked, is_paid, is_canceled)
VALUES
(1, 2, 1, null, '2023-03-12', '2023-03-14', 1, 0, '2023-03-01 13:19:26', 'Comment1', false, true, false, false, false), #booked: approved
(2, 4, 1, null, '2023-03-17', '2023-03-18', 1, 0, '2023-03-03 13:19:26', 'Comment2', false, true, true, false, false), #booked
(3, 4, 1, null, '2023-03-21', '2023-03-21', 1, 0, '2023-03-03 13:19:26', 'Comment3', false, true, true, true, false), #busy

(4, 2, 2, null, '2023-03-13', '2023-03-16', 2, 0, '2023-03-05 13:19:26', 'Comment4', false, true, true, false, false), #booked
(5, 3, 2, null, '2023-03-18', '2023-03-20', 2, 0, '2023-03-01 13:19:26', null, false, true, true, true, true), #canceled

(6, 4, 3, null, '2023-03-12', '2023-03-12', 2, 0, '2023-03-02 13:19:26', 'Comment6', false, true, true, true, true), #canceled
(7, 2, 3, null, '2023-03-15', '2023-03-15', 2, 0, '2023-03-08 13:19:26', 'Comment7', false, true, true, true, false), #busy
(8, 2, 3, null, '2023-03-18', '2023-03-19', 2, 0, '2023-03-08 13:19:26', null, false, false, false, false, false), #booked: not approved
(9, 2, 3, null, '2023-03-21', '2023-03-21', 2, 0, '2023-03-08 13:19:26', 'Comment9', false, true, true, true, false), #busy

(10, 2, 4, null, '2023-03-11', '2023-03-13', 2, 0, '2023-02-11 13:19:26', 'Comment10', false, true, true, true, false), #busy
(11, 3, 4, null, '2023-03-18', '2023-03-21', 1, 0, '2023-02-11 13:19:26', 'Comment11', false, true, true, true, false), #busy

(12, 2, 5, null, '2023-03-14', '2023-03-15', 2, 0, '2023-02-11 13:19:26', 'Comment12', false, true, true, false, false), #booked
(13, 3, 5, null, '2023-03-18', '2023-03-18', 1, 0, '2023-02-11 13:19:26', 'Comment13', false, true, true, true, true), #canceled
(14, 3, 5, null, '2023-03-20', '2023-03-21', 1, 0, '2023-02-11 13:19:26', 'Comment14', false, true, true, true, false), #busy

(15, 2, 6, null, '2023-03-15', '2023-03-15', 2, 0, '2023-02-11 13:19:26', 'Comment15', false, true, true, true, false), #busy
(16, 3, 6, null, '2023-03-18', '2023-03-18', 1, 0, '2023-02-11 13:19:26', 'Comment16', false, true, true, false, false), #booked
(17, 3, 6, null, '2023-03-20', '2023-03-21', 1, 0, '2023-02-11 13:19:26', 'Comment17', false, true, true, false, false), #booked

(18, 3, 7, null, '2023-03-12', '2023-03-13', 1, 0, '2023-02-11 13:19:26', 'Comment18', false, true, true, true, false), #busy
(19, 3, 7, null, '2023-03-17', '2023-03-18', 1, 0, '2023-02-11 13:19:26', 'Comment19', false, true, false, false, false), #booked: approved
(20, 3, 7, null, '2023-03-21', '2023-03-21', 1, 0, '2023-02-11 13:19:26', 'Comment20', false, false, false, false, false), #booked: not approved

(21, 3, 8, null, '2023-03-13', '2023-03-15', 1, 0, '2023-02-11 13:19:26', 'Comment21', false, true, true, false, false), #booked
(22, 3, 8, null, '2023-03-18', '2023-03-21', 1, 0, '2023-02-11 13:19:26', 'Comment22', false, true, true, false, false), #booked
(23, 1, null, 2, '2023-03-25', '2023-03-27', 1, 0, '2023-02-11 13:19:26', 'Comment23', false, false, false, false, false), #request
(24, 1, 11, null, '2023-03-04', '2023-03-04', 1, 0, '2023-02-11 13:19:26', 'Comment24', false, false, false, false, false), #booked: not approved
(25, 1, 12, null, '2023-03-04', '2023-03-04', 1, 0, '2023-02-11 13:19:26', 'Comment25', false, true, false, false, false), #booked: approved
(26, 1, 9, null, '2023-03-18', '2023-03-18', 1, 0, '2023-02-11 13:19:26', 'Comment26', false, true, true, true, false), #busy
(27, 1, 10, null, '2023-03-18', '2023-03-18', 1, 0, '2023-02-11 13:19:26', 'Comment27', false, false, false, false, false), #booked: not approved
(28, 1, 25, 2, '2023-03-15', '2023-03-16', 1, 0, '2023-02-28 13:22:21', 'Comment28', false, true, true, true, true), #canceled

(29, 6, NULL, NULL, '2023-05-06', '2023-05-06', 2, 0, '2023-03-01 01:22:15', 'Comment ', false, false, false, false, false), #request
(30, 6, NULL, 1, '2023-05-16', '2023-05-18', 7, 0, '2023-03-01 01:22:15', 'Comment ', false, false, false, false, false), #request
(31, 6, NULL, 1, '2023-05-06', '2023-05-06', 2, 0, '2023-03-01 01:22:15', 'Comment ', false, false, false, false, false), #request
(32, 6, NULL, 1, '2023-05-16', '2023-05-18', 4, 0, '2023-03-01 01:22:15', 'Comment ', false, false, false, false, false), #request
(33, 6, NULL, 1, '2023-05-16', '2023-05-18', 3, 0, '2023-03-01 01:22:15', 'Comment ', false, false, false, false, false), #request
(34, 6, NULL, 1, '2023-05-16', '2023-05-18', 1, 0, '2023-03-01 01:22:15', 'Comment ', false, false, false, false, false), #request
(35, 6, NULL, 1, '2023-05-16', '2023-05-18', 4, 0, '2023-03-01 01:22:15', 'Comment ', false, false, false, false, false), #request
(36, 6, NULL, 1, '2023-05-16', '2023-05-18', 7, 0, '2023-03-01 01:22:15', 'Comment ', false, false, false, false, false), #request
(37, 6, NULL, 1,  '2023-05-16', '2023-05-18', 3, 0, '2023-03-01 01:22:15', 'Comment ', false, false, false, false, false), #request
(38, 6, NULL, 1, '2023-05-06', '2023-05-06', 2, 0, '2023-03-01 01:22:15', 'Comment ', false, false, false, false, false), #request
(39, 6, NULL, 1,  '2023-05-16', '2023-05-18', 7, 0, '2023-03-01 01:22:15', 'Comment ', false, false, false, false, false), #request
(40, 6, NULL, 1, '2023-05-16', '2023-05-18', 3, 0, '2023-03-01 01:22:15', 'Comment ', false, false, false, false, false), #request
(41, 6, NULL, 1, '2023-05-16', '2023-05-18', 2, 0, '2023-03-01 01:22:15', 'Comment ', false, false, false, false, false), #request
(42, 6, NULL, 1, '2023-05-16', '2023-05-18', 2, 0, '2023-03-01 01:22:15', 'Comment ', false, false, false, false, false), #request
(43, 6, NULL, NULL, '2023-05-06', '2023-05-06', 2, 0, '2023-03-01 01:22:15', 'Comment ', false, false, false, false, false), #request
(44, 6, NULL, 1, '2023-05-16', '2023-05-18', 10, 0, '2023-03-01 01:22:15', 'Comment ', false, false, false, false, false), #request
(45, 6, NULL, 1, '2023-05-16', '2023-05-18', 1, 0, '2023-03-01 01:22:15', 'Comment ', false, false, false, false, false), #request
(46, 6, NULL, 1, '2023-05-16', '2023-05-18', 1, 0, '2023-03-01 01:22:15', 'Comment ', false, false, false, false, false), #request
(47, 6, NULL, NULL, '2023-05-06', '2023-05-06', 1, 0, '2023-03-01 01:22:15', 'Comment ', false, false, false, false, false), #request
(48, 6, NULL, 1, '2023-05-16', '2023-05-18', 6, 0, '2023-03-01 01:22:15', 'Comment ', false, false, false, false, false), #request
(49, 6, NULL, 4, '2023-05-31', '2023-05-31', 1, 0, '2023-03-02 03:12:11', 'Comment ', false, false, false, false, false), #request
(50, 6, NULL, NULL, '2023-05-30', '2023-05-30', 4, 0, '2023-03-02 04:58:51', 'Comment ', false, false, false, false, false), #request
(51, 6, NULL, 2, '2023-05-16', '2023-05-16', 1, 0, '2023-03-02 04:59:00', 'Comment ', false, false, false, false, false), #request
(52, 6, NULL, NULL, '2023-05-06', '2023-05-06', 3, 0, '2023-03-02 04:59:06', 'Comment ', false, false, false, false, false), #request
(53, 6, NULL, NULL, '2023-06-21', '2023-06-29', 2, 0, '2023-03-02 04:59:16', 'Comment ', false, false, false, false, false), #request
(54, 6, NULL, NULL, '2023-05-31', '2023-05-31', 8, 0, '2023-03-02 04:59:26', 'Comment ', false, false, false, false, false), #request
(55, 6, NULL, 2, '2023-05-06', '2023-05-06', 2, 0, '2023-03-02 04:59:34', 'Comment ', false, false, false, false, false), #request
(56, 6, NULL, NULL, '2023-05-09', '2023-05-09', 1, 0, '2023-03-02 04:59:40', 'Comment ', false, false, false, false, false), #request
(57, 6, NULL, NULL, '2023-05-31', '2023-05-31', 1, 0, '2023-03-02 04:59:45', 'Comment ', false, false, false, false, false), #request
(58, 6, NULL, NULL, '2023-05-22', '2023-05-25', 2, 0, '2023-03-02 04:59:54', 'Comment ', false, false, false, false, false), #request
(59, 6, NULL, 1, '2023-07-18', '2023-07-27', 3, 0, '2023-03-03 02:05:39', 'Comment ', false, false, false, false, false), #request
(60, 6, NULL, 5, '2023-05-16', '2023-05-24', 2, 0, '2023-03-03 02:06:50', 'Comment ', false, false, false, false, false), #request
(61, 6, NULL, 4, '2023-05-16', '2023-05-24', 3, 0, '2023-03-03 02:06:03', 'Comment ', false, false, false, false, false), #request
(62, 6, NULL, NULL, '2023-05-06', '2023-05-06', 1, 0, '2023-03-03 02:07:08', 'Comment ', false, false, false, false, false); #request

INSERT INTO tags (id, name, is_basic, description)
VALUES
(1, 'Toiletries', true, ''),
(2, 'Bathrobe', false, ''),
(3, 'Safe', false, ''),
(4, 'Toilet', true, ''),
(5, 'Sofa', false, ''),
(6, 'Bath or shower', true, ''),
(7, 'Towels', true, ''),
(8, 'Linen', false, ''),
(9, 'Socket near the bed', true, ''),
(10, 'Cleaning products', false, ''),
(11, 'Safe for allergy sufferers', false, ''),
(12, 'Work desk', false, ''),
(13, 'Living room / seating area', false, ''),
(14, 'TV set', false, ''),
(15, 'Slippers', false, ''),
(16, 'Refrigerator', false, ''),
(17, 'Telephone', false, ''),
(18, 'Accessories for ironing clothes', false, ''),
(19, 'Satellite channels', false, ''),
(20, 'Kettle / coffee maker', false, ''),
(21, 'Iron', false, ''),
(22, 'Heating', false, ''),
(23, 'Hair dryer', false, ''),
(24, 'Towels / bed linen for an additional fee', false, ''),
(25, 'Carpeted flooring', false, ''),
(26, 'Electric kettle', false, ''),
(27, 'Cable channels', false, ''),
(28, 'Wake-up call service', false, ''),
(29, 'Wardrobe or wardrobe', false, ''),
(30, 'The higher floors can be reached by elevator', false, ''),
(31, 'A hanger for clothes', false, ''),
(32, 'Toilet paper', false, ''),
(33, 'Sofa bed', false, ''),
(34, 'Individual air conditioning in guest rooms', false, '');


INSERT INTO apartments_tags (id, tag_id, apartment_id)
VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),

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
