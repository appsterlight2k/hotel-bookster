package com.appsterlight.db;

public class Fields {
    public static final String ID = "id";

    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME= "last_name";
    public static final String USER_EMAIL= "email";
    public static final String USER_PHONE_NUMBER = "phone_number";
    public static final String USER_PASSWORD = "password";
    public static final String USER_ROLE = "role";

    /* CONNECTION PROPERTIES FIELDS */
    public static final String DB_URL = "connection.url";
    public static final String DB_USERNAME = "connection.username";
    public static final String DB_PASSWORD = "connection.password";
    public static final String DB_DRIVER = "connection.driver";
    public static final String DB_MAXIMUM_POOL_SIZE = "connection.maximumPoolSize";
    public static final String DB_IDLE_TIMEOUT = "connection.idleTimeout";
    public static final String DB_AUTO_COMMIT = "connection.autoCommit";
    public static final String DB_CACHE_PREP_STMTS = "dataSource.cachePrepStmts";
    public static final String DB_PREP_STMT_CACHE_SIZE = "dataSource.prepStmtCacheSize";
    public static final String DB_PREP_STMT_CACHE_SQL_LIMIT = "dataSource.prepStmtCacheSqlLimit";
}
