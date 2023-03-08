package com.appsterlight.model.db.constants;

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
            "INSERT INTO booking (user_id, apartment_id, check_in, check_out, adults_number, children_number, " +
                    "reservation_time, comments, is_approved, is_booked, is_paid, is_canceled) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String SQL_BOOKING_UPDATE =
            "UPDATE booking SET " +
                    "user_id = ?, apartment_id = ?, check_in = ?, check_out = ?, adults_number = ?, children_number = ?, " +
                    "reservation_time = ?, comments= ?, is_approved = ?, is_booked = ?, is_paid = ?, is_canceled = ? " +
                    "WHERE ID = ?";


    /* QUERIES FOR APARTMENT TABLE */
    public static final String SQL_APARTMENT_GET =
            "SELECT a.*, c.name as class_name, c.description as class_description " +
            "FROM apartments a INNER JOIN apartment_class c ON a.class_id = c.id WHERE a.id = ?";

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

    //2nd parameter is check_in of user request, and th 3rd is check_out!
    public static final String SQL_APARTMENT_GET_ALL_FREE_BY_CAPACITY =
            "SELECT a.*, c.name as class_name, c.description as class_description " +
            "FROM apartments a " +
            "LEFT JOIN booking b ON a.id = b.apartment_id AND b.check_out >= ? AND b.check_in <= ? " +
            "INNER JOIN apartment_class c ON a.class_id = c.id " +
            "WHERE a.adults_capacity >= ? AND b.apartment_id IS NULL";

    //use QueryBuilder to add additional conditions
    public static final String SQL_APARTMENT_GET_ALL_APARTMENTS_BY_TAG_ID =
            "SELECT a.* FROM apartments a JOIN apartments_tags ON " +
                    "apartments.id = apartments_tags.apartment_id WHERE apartments_tags.tag_id = ? %s";

    public static final String SQL_BOOKING_GET_ALL_FREE_ = "SELECT * FROM booking WHERE id = ? " +
            "AND check_in > ? AND check_out < ? AND is_approved = false AND is_booked = false " +
            "AND is_paid = false AND is_canceled = false";
    public static final String SQL_BOOKING_GET_IS_APPROVED = "SELECT * FROM booking WHERE id = ? " +
            "AND check_in > ? AND check_out < ? AND is_approved = ?";
    public static final String SQL_BOOKING_GET_IS_BOOKED = "SELECT * FROM booking WHERE id = ? " +
            "AND check_in > ? AND check_out < ? AND is_booked = ?";
    public static final String SQL_BOOKING_GET_IS_PAID = "SELECT * FROM booking WHERE id = ? " +
            "AND check_in > ? AND check_out < ? AND is_paid = ?";
    public static final String SQL_BOOKING_GET_IS_CANCELED = "SELECT * FROM booking WHERE id = ? " +
            "AND check_in > ? AND check_out < ? AND is_canceled = ?";


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
            "SELECT tags.* FROM tags JOIN apartments_tags ON tags.id = apartments_tags.tag_id " +
                    "WHERE apartments_tags.apartment_id = ?";

    //for editing tags functionality
    public static final String SQL_APARTMENT_TAGS_GET_ALL_APARTMENT_TAGS_BY_TAG_ID =
            "SELECT * FROM apartment_tags WHERE tag_id = ?";

    /* INVOICE CHECKING EVENT QUERY */
    public static final String SQL_BOOKING_CREATE_EVENT_IS_PAID = "CREATE EVENT %s " +
            "ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 1 MINUTE " +
            "DO " +
            "DELETE FROM booking WHERE id = ? AND is_paid = false";

}
