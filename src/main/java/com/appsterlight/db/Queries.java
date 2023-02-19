package com.appsterlight.db;

public class Queries {
    public static final String SQL_USER_INSERT =
            "INSERT INTO users (first_name, last_name, email, phone_number, password, role, description) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static final String SQL_USER_GET = "SELECT * FROM users WHERE id = ?";
    public static final String SQL_USER_UPDATE =
            "UPDATE users SET " +
            "first_name = ?, last_name = ?, email = ?, phone_number = ?, password = ?, role = ?, description = ?  " +
            "WHERE ID = ?";

    public static final String SQL_USER_DELETE = "DELETE FROM users WHERE id = ?";
    public static final String SQL_USER_GET_ALL = "SELECT * FROM users";


}
