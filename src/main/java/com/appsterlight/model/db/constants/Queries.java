package com.appsterlight.model.db.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Queries {

    /* QUERIES FOR USER TABLE */
    public static final String SQL_USER_GET = "SELECT * FROM users WHERE id = ?";
    public static final String SQL_USER_DELETE = "DELETE FROM users WHERE id = ?";
    public static final String SQL_USER_GET_ALL = "SELECT * FROM users";
    public static final String SQL_USER_INSERT =
            "INSERT INTO users (first_name, last_name, email, phone_number, password, role, description) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static final String SQL_USER_UPDATE =
            "UPDATE users SET " +
            "first_name = ?, last_name = ?, email = ?, phone_number = ?, password = ?, role = ?, description = ?  " +
            "WHERE ID = ?";

    public static final String SQL_USER_GET_BY_EMAIL = "SELECT * FROM users WHERE email = ?";


    /* QUERIES FOR BOOKING TABLE */
    public static final String SQL_BOOKING_GET = "SELECT * FROM booking WHERE id = ?";
    public static final String SQL_BOOKING_GET_BOOKING_COUNT = "SELECT COUNT(*) FROM booking WHERE " +
            "user_id = ? AND apartment_id = ? AND check_in = ? AND check_out = ? AND adults_number = ?";

    public static final String SQL_BOOKING_DELETE = "DELETE FROM booking WHERE id = ?";
    public static final String SQL_BOOKING_GET_ALL = "SELECT * FROM booking";
    public static final String SQL_BOOKING_INSERT =
            "INSERT INTO booking (user_id, apartment_id, request_class_id, check_in, check_out, adults_number, children_number, " +
                    "reservation_time, comments, is_offered, is_approved, is_booked, is_paid, is_canceled) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String SQL_BOOKING_UPDATE =
            "UPDATE booking SET " +
                    "user_id = ?, apartment_id = ?, request_class_id = ?, check_in = ?, check_out = ?, adults_number = ?, children_number = ?, " +
                    "reservation_time = ?, comments= ?, is_offered = ?, is_approved = ?, is_booked = ?, is_paid = ?, is_canceled = ? " +
                    "WHERE ID = ?";

    public static final String SQL_BOOKING_SET_IF_OFFERED =
            "UPDATE booking SET is_offered = ? WHERE ID = ?";

    public static final String SQL_BOOKING_GET_ALL_BOOKING_REQUESTS_BASE =
            "SELECT b.*, " +
            "u.first_name, " +
            "u.last_name, " +
            "u.email, " +
            "phone_number AS user_phone_number, " +
            "u.description as user_description, " +
            "a.apartment_number, " +
            "a.price, " +
            "a.rooms_count, " +
            "a.adults_capacity, " +
            "c.name AS class " +
            "FROM booking b " +
            "LEFT JOIN users u ON b.user_id = u.id " +
            "LEFT JOIN apartments a ON b.apartment_id = a.id " +
            "LEFT JOIN apartment_class c ON a.class_id = c.id " +
            "WHERE apartment_id IS NOT NULL AND is_approved = false ";

    public static final String SQL_BOOKING_GET_ALL_BOOKING_REQUESTS =
            SQL_BOOKING_GET_ALL_BOOKING_REQUESTS_BASE + "ORDER BY check_in ASC ";

    public static final String SQL_BOOKING_GET_ALL_BOOKING_REQUESTS_WITH_PAGINATION =
            SQL_BOOKING_GET_ALL_BOOKING_REQUESTS + "LIMIT ?, ?";
    public static final String SQL_BOOKING_GET_ALL_BOOKING_REQUESTS_AFTER_DATE_WITH_PAGINATION =
            SQL_BOOKING_GET_ALL_BOOKING_REQUESTS_BASE +
                    "AND check_in >= ? " +
                    "ORDER BY check_in ASC " +
                    "LIMIT ?, ?";
    public static final String SQL_BOOKING_GET_ALL_BOOKING_REQUESTS_BY_PERIOD_AND_PAGINATION =
            SQL_BOOKING_GET_ALL_BOOKING_REQUESTS_BASE +
                    "AND check_in >= ? AND check_out <= ? ORDER BY check_in ASC " +
                    "LIMIT ?, ?";

    public static final String SQL_BOOKING_GET_ALL_REQUESTS_FOR_BOOKING_BASE =
            "SELECT b.*, " +
                    "u.first_name, " +
                    "u.last_name, " +
                    "u.email, " +
                    "phone_number AS user_phone_number, " +
                    "u.description as user_description, " +
                    "IFNULL(c.name, 'Any') AS class " +
                    "FROM booking b " +
                    "LEFT JOIN users u ON b.user_id = u.id " +
                    "LEFT JOIN apartment_class c ON b.request_class_id = c.id " +
                    "WHERE apartment_id IS NULL AND is_offered = false AND is_approved = false ";


    public static final String SQL_BOOKING_GET_ALL_REQUESTS_FOR_BOOKING =
            SQL_BOOKING_GET_ALL_REQUESTS_FOR_BOOKING_BASE + "ORDER BY check_in ASC ";

    public static final String SQL_BOOKING_GET_ALL_REQUESTS_FOR_BOOKING_WITH_PAGINATION =
            SQL_BOOKING_GET_ALL_REQUESTS_FOR_BOOKING + "LIMIT ?, ?";

    public static final String SQL_BOOKING_GET_ALL_REQUESTS_FOR_BOOKING_AFTER_DATE_WITH_PAGINATION =
            SQL_BOOKING_GET_ALL_REQUESTS_FOR_BOOKING_BASE +
                    "AND check_in >= ? " +
                    "ORDER BY check_in ASC " +
                    "LIMIT ?, ?";
    public static final String SQL_BOOKING_GET_ALL_REQUESTS_FOR_BOOKING_BY_PERIOD_AND_PAGINATION =
            SQL_BOOKING_GET_ALL_REQUESTS_FOR_BOOKING_BASE +
                    "AND check_in >= ? AND check_out <= ? ORDER BY check_in ASC " +
                    "LIMIT ?, ?";

    public static final String SQL_BOOKING_GET_COUNT_OF_ALL_BOOKING_REQUEST =
            "SELECT COUNT(*) FROM booking WHERE apartment_id IS NOT NULL AND is_approved = false";
    public static final String SQL_BOOKING_GET_COUNT_OF_ALL_BOOKING_REQUEST_AFTER_DATE =
            "SELECT COUNT(*) FROM booking WHERE apartment_id IS NOT NULL AND is_approved = false AND check_in >= ?";

    public static final String SQL_BOOKING_GET_COUNT_OF_ALL_BOOKING_REQUEST_BY_PERIOD =
            "SELECT COUNT(*) FROM booking WHERE apartment_id IS NOT NULL AND is_approved = false " +
                    "AND check_in >= ? AND check_out <= ?";

    public static final String SQL_BOOKING_GET_COUNT_OF_ALL_REQUEST_FOR_BOOKING =
            "SELECT COUNT(*) FROM booking WHERE apartment_id IS NULL AND is_offered = false AND is_approved = false";

    public static final String SQL_BOOKING_GET_COUNT_OF_ALL_REQUEST_FOR_BOOKING_AFTER_DATE =
            "SELECT COUNT(*) FROM booking WHERE apartment_id IS NULL AND is_offered = false AND is_approved = false " +
                    "AND check_in >= ?";

    public static final String SQL_BOOKING_GET_COUNT_OF_ALL_REQUEST_FOR_BOOKING_BY_PERIOD =
            "SELECT COUNT(*) FROM booking WHERE apartment_id IS NULL AND is_offered = false AND is_approved = false " +
                    "AND check_in >= ? AND check_out <= ?";

    public static final String SQL_BOOKING_GET_IS_OFFERED = "SELECT * FROM booking WHERE id = ? " +
            "is_offered = true AND is_approved = false";
    public static final String SQL_BOOKING_GET_IS_APPROVED = "SELECT * FROM booking WHERE id = ? " +
            "AND check_in > ? AND check_out < ? AND is_approved = ?";
    public static final String SQL_BOOKING_GET_IS_BOOKED = "SELECT * FROM booking WHERE id = ? " +
            "AND check_in > ? AND check_out < ? AND is_booked = ?";
    public static final String SQL_BOOKING_GET_IS_PAID = "SELECT * FROM booking WHERE id = ? " +
            "AND check_in > ? AND check_out < ? AND is_paid = ?";
    public static final String SQL_BOOKING_GET_IS_CANCELED = "SELECT * FROM booking WHERE id = ? " +
            "AND check_in > ? AND check_out < ? AND is_canceled = ?";


    /* QUERIES FOR OFFERED_APARTMENTS TABLE*/
    public static final String SQL_OFFERED_APARTMENTS_GET_BY_BOOKING_ID = "SELECT * FROM offered_apartments WHERE booking_id = ?";
    public static final String SQL_OFFERED_APARTMENTS_GET = "SELECT * FROM offered_apartments WHERE id = ?";
    public static final String SQL_OFFERED_APARTMENTS_DELETE = "DELETE FROM offered_apartments WHERE id = ?";
    public static final String SQL_OFFERED_APARTMENTS_GET_ALL = "SELECT * FROM offered_apartments";
    public static final String SQL_OFFERED_APARTMENTS_INSERT =
            "INSERT INTO offered_apartments (booking_id, apartment_id, message) VALUES (?, ?, ?)";
    public static final String SQL_OFFERED_APARTMENTS_UPDATE =
            "UPDATE offered_apartments SET booking_id = ?, apartment_id = ?, message = ? WHERE ID = ?";


    /* QUERIES FOR APARTMENT TABLE */
    public static final String SQL_APARTMENT_GET =
            "SELECT a.*, c.name as class_name, c.description as class_description " +
            "FROM apartments a " +
            "INNER JOIN apartment_class c ON a.class_id = c.id WHERE a.id = ?";

    public static final String SQL_APARTMENT_GET_BY_APARTMENT_NUMBER =
            "SELECT a.*, c.name as class_name, c.description as class_description " +
            "FROM apartments a INNER JOIN apartment_class c ON a.class_id = c.id WHERE a.apartment_number = ?";
    public static final String SQL_APARTMENT_DELETE = "DELETE FROM apartments WHERE id = ?";
    public static final String SQL_APARTMENT_GET_ALL =
            "SELECT a.*, c.name as class_name, c.description as class_description " +
            "FROM apartments a INNER JOIN apartment_class c ON a.class_id = c.id";
    public static final String SQL_APARTMENT_INSERT =
            "INSERT INTO apartments (apartment_number, rooms_count, class_id, adults_capacity, " +
                    "children_capacity, main_photo_url, price, description) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_APARTMENT_UPDATE =
            "UPDATE apartments SET " +
                    "apartment_number = ?, rooms_count = ?, class_id = ?, adults_capacity = ?, " +
                    "children_capacity = ?, main_photo_url = ?, price = ?, description = ? " +
                    "WHERE ID = ?";

    //Parameters:
    //1st: checkin date; 2nd: checkout date; 3rd: adults_capacity; 4th: class_id; 5th: status;
    //6th: sorting field; 7th: asc/desc order
    public static final String SQL_APARTMENT_GET_ALL_WITH_STATUS_FULL =
        "SELECT * FROM ( " +
            "SELECT a.*, b.id as booking_id, b.is_approved, b.is_booked, b.is_paid, b.is_canceled, " +
                "c.name as class_name, c.description as class_description, " +
            "CASE " +
                "WHEN b.is_canceled = true THEN 'free' " +
                "WHEN b.id IS NOT NULL THEN 'booked' " +
                "WHEN b.is_paid = true THEN 'busy' " +
                "WHEN a.is_unavailable = true THEN 'unavailable' " +
                "ELSE 'free' " +
            "END AS status " +
            "FROM apartments a " +
            "LEFT JOIN booking b ON a.id = b.apartment_id " +
                "AND b.check_out >= ? " +
                "AND b.check_in <= ? " +
            "INNER JOIN apartment_class c ON a.class_id = c.id " +
        ") AS subquery " +
        "WHERE subquery.adults_capacity >= ? " +
            "AND (subquery.class_id = ? OR ? IS NULL) " +
            "AND subquery.status LIKE ? " +
            "AND subquery.id NOT IN (SELECT apartment_id FROM offered_apartments)";

    public static final String SQL_APARTMENT_GET_COUNT_OF_ALL_WITH_STATUS_FULL =
            "SELECT COUNT(*) FROM ( " +
                "SELECT a.*, b.id as booking_id, b.is_approved, b.is_booked, b.is_paid, b.is_canceled, " +
                "CASE " +
                    "WHEN b.is_canceled = true THEN 'free' " +
                    "WHEN b.id IS NOT NULL THEN 'booked' " +
                    "WHEN b.is_paid = true THEN 'busy' " +
                    "WHEN a.is_unavailable = true THEN 'unavailable' " +
                    "ELSE 'free' " +
                "END AS status " +
                "FROM apartments a " +
                    "LEFT JOIN booking b ON a.id = b.apartment_id " +
                "AND b.check_out >= ? " +
                "AND b.check_in <= ? " +
            ") AS subquery " +
            "WHERE subquery.adults_capacity >= ? " +
                    "AND (subquery.class_id = ? OR ? IS NULL) " +
                    "AND subquery.status LIKE ? " +
                    "AND subquery.id NOT IN (SELECT apartment_id FROM offered_apartments)";

    /* QUERIES FOR APARTMENT_CLASS TABLE */
    public static final String SQL_APARTMENT_CLASS_GET = "SELECT * FROM apartment_class WHERE id = ?";
    public static final String SQL_APARTMENT_CLASS_DELETE = "DELETE FROM apartment_class WHERE id = ?";
    public static final String SQL_APARTMENT_CLASS_GET_ALL = "SELECT * FROM apartment_class";
    public static final String SQL_APARTMENT_CLASS_INSERT =
            "INSERT INTO apartment_class (name, description) VALUES (?, ?)";
    public static final String SQL_APARTMENT_CLASS_UPDATE =
            "UPDATE apartment_class SET name = ?, description = ? WHERE ID = ?";

    
    //use QueryBuilder to add additional conditions
    public static final String SQL_APARTMENT_GET_ALL_APARTMENTS_BY_TAG_ID =
            "SELECT a.* FROM apartments a JOIN apartments_tags ON " +
                    "apartments.id = apartments_tags.apartment_id WHERE apartments_tags.tag_id = ? %s";

    /* QUERIES FOR APARTMENT_PHOTOS TABLE */
    public static final String SQL_APARTMENT_PHOTOS_GET =
            "SELECT * FROM apartments_photos WHERE id = ?";
    public static final String SQL_APARTMENT_PHOTOS_DELETE =
            "DELETE FROM apartments_photos WHERE id = ?";
    public static final String SQL_APARTMENT_PHOTOS_GET_ALL =
            "SELECT * FROM apartments_photos";
    public static final String SQL_APARTMENT_PHOTOS_INSERT =
            "INSERT INTO apartments_photos (apartment_id, path) VALUES (?, ?)";
    public static final String SQL_APARTMENT_PHOTOS_UPDATE =
            "UPDATE apartments_photos SET apartment_id = ?, path = ? WHERE ID = ?";
    public static final String SQL_APARTMENT_PHOTOS_GET_ALL_PHOTOS_BY_ID
            = "SELECT * FROM apartments_photos WHERE apartment_id = ?";


    /* QUERIES FOR TAGS TABLE */
    public static final String SQL_TAGS_GET = "SELECT * FROM tags WHERE id = ?";
    public static final String SQL_TAGS_DELETE = "DELETE FROM tags WHERE id = ?";
    public static final String SQL_TAGS_GET_ALL = "SELECT * FROM tags";
    public static final String SQL_TAGS_INSERT = "INSERT INTO tags (name, description) VALUES (?, ?)";
    public static final String SQL_TAGS_UPDATE = "UPDATE tags SET name = ?, description = ? WHERE ID = ?";


    /* QUERIES FOR APARTMENT_TAGS TABLE */
    public static final String SQL_APARTMENT_TAGS_GET = "SELECT * FROM apartment_tags WHERE id = ?";
    public static final String SQL_APARTMENT_TAGS_DELETE = "DELETE FROM apartment_tags WHERE id = ?";
    public static final String SQL_APARTMENT_TAGS_GET_ALL = "SELECT * FROM apartment_tags";
    public static final String SQL_APARTMENT_TAGS_INSERT = "INSERT INTO apartment_tags (name, description) VALUES (?, ?)";
    public static final String SQL_APARTMENT_TAGS_UPDATE = "UPDATE apartment_tags SET name = ?, description = ? WHERE ID = ?";

    public static final String SQL_APARTMENT_TAGS_GET_ALL_TAGS_BY_APARTMENT_ID =
            "SELECT tags.* " +
            "FROM tags " +
            "JOIN apartments_tags ON tags.id = apartments_tags.tag_id " +
            "WHERE apartments_tags.apartment_id = ?";

    public static final String SQL_APARTMENT_TAGS_GET_ALL_TAGS_BY_TYPE_AND_APARTMENT_ID =
            "SELECT tags.* " +
                    "FROM tags " +
                    "JOIN apartments_tags ON tags.id = apartments_tags.tag_id " +
                    "WHERE apartments_tags.apartment_id = ? AND tags.is_basic = ?";

    //for editing tags functionality
    public static final String SQL_APARTMENT_TAGS_GET_ALL_APARTMENT_TAGS_BY_TAG_ID =
            "SELECT * FROM apartment_tags WHERE tag_id = ?";

    /* INVOICE CHECKING EVENT QUERY */
    public static final String SQL_BOOKING_CREATE_EVENT_IS_PAID = "CREATE EVENT %s " +
            "ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 1 MINUTE " +
            "DO " +
            "DELETE FROM booking WHERE id = ? AND is_paid = false";

}
