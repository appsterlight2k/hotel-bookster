package com.appsterlight.model.db.connection;

import java.sql.Connection;

public interface DBManager {
    abstract Connection getConnection();
}
