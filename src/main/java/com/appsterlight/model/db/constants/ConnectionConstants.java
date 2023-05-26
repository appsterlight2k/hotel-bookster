package com.appsterlight.model.db.constants;

public class ConnectionConstants {
    public static final String APP_SETTINGS_FILE = "app.properties";

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
