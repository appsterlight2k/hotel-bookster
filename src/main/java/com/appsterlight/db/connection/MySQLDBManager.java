package com.appsterlight.db.connection;

import com.appsterlight.Constants;
import com.appsterlight.exceptions.PropertiesException;
import com.appsterlight.exceptions.DBException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class MySQLDBManager extends AbstractDBManager {
    private static MySQLDBManager instance = null;
    private MySQLDBManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            log.error("Can't load BD driver: " + e.getMessage());
            throw new DBException("Can't load BD driver", e);
        }
    }

    public Connection getConnection() {
        String URL = getURLFromProperties();
        try {
            return  DriverManager.getConnection(URL);
        } catch (SQLException e) {
            log.error(String.format("Connection Exception: Can't establish connection to database %s", e.getMessage()));
            throw new DBException("Can't establish connection to database", e);
        }
    }

    public static synchronized MySQLDBManager getInstance() {
        if (instance == null) {
            instance = new MySQLDBManager();
        }
        return instance;
    }

    private static String getURLFromProperties()  {
        Properties appProps = new Properties();
        try (InputStream propFile = MySQLDBManager.class.getClassLoader().getResourceAsStream(Constants.SETTINGS_FILE)) {
            appProps.load(propFile);
            String URL = appProps.getProperty("connection.url");
            String user = appProps.getProperty("connection.username");
            String pass =  appProps.getProperty("connection.password");

            return URL + "?user=" + user + "&password=" + pass;
        } catch (IOException e) {
            log.error("Application properties Exception: Can't read app.properties from file", e);
            throw new PropertiesException("Can't read app.properties from file; " + e);
        }

    }

}
