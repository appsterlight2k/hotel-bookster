package com.appsterlight.model.db.constants;

public class Fields {
    /* GENERAL FIELDS */
    public static final String ID = "id";
    public static final String DESCRIPTION = "description";


    /* SPECIFIC FIELDS FOR USER TABLE */
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME= "last_name";
    public static final String USER_EMAIL= "email";
    public static final String USER_PHONE_NUMBER = "phone_number";
    public static final String USER_PASSWORD = "password";
    public static final String USER_ROLE = "role";


    /* SPECIFIC FIELDS FOR BOOKING TABLE */
    public static final String BOOKING_USER_ID = "user_id";
    public static final String BOOKING_APARTMENT_ID = "apartment_id";
    public static final String BOOKING_CHECK_IN = "check_out";
    public static final String BOOKING_CHECK_OUT = "check_in";
    public static final String BOOKING_ADULTS_NUMBER = "adults_number";
    public static final String BOOKING_CHILDREN_NUMBER = "children_number";
    public static final String BOOKING_RESERVATION_TIME = "reservation_time";
    public static final String BOOKING_IS_APPROVED = "is_approved";
    public static final String BOOKING_IS_BOOKED = "is_booked";
    public static final String BOOKING_IS_PAID = "is_paid";
    public static final String BOOKING_IS_CANCELED = "is_canceled";


    /* SPECIFIC FIELDS FOR APARTMENT TABLE */
    public static final String APARTMENT_NUMBER = "apartment_number";
    public static final String APARTMENT_ROOMS_COUNT = "rooms_count";
    public static final String APARTMENT_CLASS_ID = "class_id";
    public static final String APARTMENT_ADULTS_CAPACITY = "adults_capacity";
    public static final String APARTMENT_CHILDREN_CAPACITY = "children_capacity";
    public static final String APARTMENT_PRICE = "price";
    //DESCRIPTION

    /* SPECIFIC FIELDS FOR APARTMENT_PHOTOS TABLE */
    public static final String APARTMENT_PHOTOS_APARTMENT_ID = "apartment_id";
    public static final String APARTMENT_PHOTOS_PATH = "path";


    //                           *** CONNETCION ***
    /* CONNECTION PROPERTIES FIELDS */
    public static final String DB_URL = "connection.url";
    public static final String DB_USERNAME = "connection.username";
    public static final String DB_PASSWORD = "connection.password";
    public static final String DB_DRIVER = "connection.driver";
    public static final String DB_MAXIMUM_POOL_SIZE = "connection.maximumPoolSize";
    //for testing purposes only:
    public static final String SET_LEAK_DETECTION_THRESHOLD = "connection.setLeakDetectionThreshold";
    public static final String DB_CACHE_PREP_STMTS = "dataSource.cachePrepStmts";
    public static final String DB_PREP_STMT_CACHE_SIZE = "dataSource.prepStmtCacheSize";
    public static final String DB_PREP_STMT_CACHE_SQL_LIMIT = "dataSource.prepStmtCacheSqlLimit";
}
