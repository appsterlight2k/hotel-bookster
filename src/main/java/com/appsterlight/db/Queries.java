package com.appsterlight.db;

public class Queries {

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


    /* QUERIES FOR BOOKING TABLE */
    public static final String SQL_BOOKING_GET = "SELECT * FROM booking WHERE id = ?";
    public static final String SQL_BOOKING_DELETE = "DELETE FROM booking WHERE id = ?";
    public static final String SQL_BOOKING_GET_ALL = "SELECT * FROM booking";
    public static final String SQL_BOOKING_INSERT =
            "INSERT INTO booking (user_id, apartment_id, check_in, check_out, adults_number, children_number, " +
                    "reservation_time, is_approved, is_booked, is_paid, is_canceled) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String SQL_BOOKING_UPDATE =
            "UPDATE booking SET " +
                    "user_id = ?, apartment_id = ?, check_in = ?, check_out = ?, adults_number = ?, children_number = ?, " +
                    "reservation_time = ?, is_approved = ?, is_booked = ?, is_paid = ?, is_canceled = ? " +
                    "WHERE ID = ?";


    /* QUERIES FOR APARTMENT TABLE */
    public static final String SQL_APARTMENT_GET = "SELECT * FROM apartment WHERE id = ?";
    public static final String SQL_APARTMENT_DELETE = "DELETE FROM apartment WHERE id = ?";
    public static final String SQL_APARTMENT_GET_ALL = "SELECT * FROM apartment";
    public static final String SQL_APARTMENT_INSERT =
            "INSERT INTO apartment (apartment_number, rooms_count, class_id, adults_capacity, children_capacity, price, description) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static final String SQL_APARTMENT_UPDATE =
            "UPDATE apartment SET " +
                    "apartment_number = ?, rooms_count = ?, class_id = ?, adults_capacity = ?, children_capacity = ?, price = ?, description = ? " +
                    "WHERE ID = ?";

}
