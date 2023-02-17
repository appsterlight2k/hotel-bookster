package com.appsterlight.db.connection;

import com.appsterlight.Constants;
import com.appsterlight.exceptions.PropertiesException;
import com.appsterlight.exceptions.DBException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLDBManager extends AbstractDBManager {
    private static MySQLDBManager instance = null;

    private MySQLDBManager() {}

    @Override
    public Connection getConnection() {
        String URL = getProperties();
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new DBException("Can't establish connection to database", e);
            //LOG.error()
        }
    }

    public static synchronized MySQLDBManager getInstance() {
        if (instance == null) {
            instance = new MySQLDBManager();
        }
        return instance;
    }

    private static String getProperties()  {
        Properties appProps = new Properties();
        try (InputStream propFile = new FileInputStream(Constants.SETTINGS_FILE)) {
            appProps.load(propFile);
            String URL = appProps.getProperty("connection.url");
            String user = appProps.getProperty("connection.username");
            String pass =  appProps.getProperty("connection.password");
            return URL + "?user=" + user + "&password=" + pass;

//            connection.url=jdbc:mysql://localhost:3306/hotel?user=root&password=root
        } catch (IOException e) {
            throw new PropertiesException("Can't read app.properties from file; " + e);
            //LOG.error()...
        }


    }

}
