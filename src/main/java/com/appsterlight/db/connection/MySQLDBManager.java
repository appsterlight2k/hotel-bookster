package com.appsterlight.db.connection;

import com.appsterlight.Constants;
import com.appsterlight.db.Fields;
import com.appsterlight.exceptions.PropertiesException;
import com.appsterlight.exceptions.DBException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import java.sql.SQLException;
import java.util.Properties;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MySQLDBManager implements DBManager {
    private static HikariDataSource dataSource;
    private static MySQLDBManager instance = null;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            Properties props = getProperties();
            HikariConfig config = getHikariConfig(props);
            try {
                dataSource = new HikariDataSource(config);
            } catch (Exception e) {
                log.error("Can't get DataSource!", e.getMessage());
            }

//            log.info("Hikari Pool was successfully configured and created");
        }
        return dataSource;
    }

    public static synchronized MySQLDBManager getInstance() {
        if (instance == null) {
            instance = new MySQLDBManager();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            log.error(String.format("Connection Exception: Can't establish connection to database %s", e.getMessage()));
            throw new DBException("Can't establish connection to database", e);
        }
    }

    private static Properties getProperties()  {
        try (InputStream propFile = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(Constants.APP_SETTINGS_FILE)) {
            Properties appProperties = new Properties();
            appProperties.load(propFile);

            return appProperties;
        } catch (IOException e) {
            log.error("Application properties Exception: Can't read app.properties from file", e);
            throw new PropertiesException("Can't read app.properties from file; " + e);
        }
    }

    private static HikariConfig getHikariConfig(Properties props) {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(props.getProperty(Fields.DB_URL));
        hikariConfig.setUsername(props.getProperty(Fields.DB_USERNAME));
        hikariConfig.setPassword(props.getProperty(Fields.DB_PASSWORD));
        hikariConfig.setDriverClassName(props.getProperty(Fields.DB_DRIVER));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(props.getProperty(Fields.DB_MAXIMUM_POOL_SIZE)));
//        hikariConfig.setAutoCommit(Boolean.parseBoolean(props.getProperty(Fields.DB_AUTO_COMMIT)));
//        hikariConfig.setIdleTimeout(Integer.parseInt(props.getProperty(Fields.DB_IDLE_TIMEOUT)) * 1000); //in sec.
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit",
                props.getProperty(Fields.DB_CACHE_PREP_STMTS));
        hikariConfig.addDataSourceProperty("prepStmtCacheSize",
                props.getProperty(Fields.DB_PREP_STMT_CACHE_SIZE));
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit",
                props.getProperty(Fields.DB_PREP_STMT_CACHE_SQL_LIMIT));

//        log.info("Hikari Pool configuration was successfully created!");

        return hikariConfig;
    }


}
