package com.appsterlight.db.connection;

import java.sql.Connection;

public interface DBManager {
    abstract Connection getConnection();
}
