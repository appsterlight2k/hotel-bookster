package com.appsterlight.db.connection;

import java.sql.Connection;

public abstract class AbstractDBManager {
    abstract Connection getConnection();
}
