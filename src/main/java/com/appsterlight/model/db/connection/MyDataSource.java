package com.appsterlight.model.db.connection;

import com.appsterlight.model.db.constants.Constants;
import com.appsterlight.exception.PropertiesException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.appsterlight.model.db.constants.ConnectionConstants.*;
import static com.appsterlight.model.db.constants.Constants.*;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MyDataSource {
    private static HikariDataSource dataSource;
    private static MyDataSource instance = null;
    private static final Map<String, String> settings = new HashMap<>();

    public DataSource getDataSource() {
        if (dataSource == null) {
            Properties props = getProperties();
            HikariConfig config = getHikariConfig(props);
            try {
                dataSource = new HikariDataSource(config);
            } catch (Exception e) {
                log.error(String.format("Can't get DataSource! %s", e.getMessage()));
            }

            log.info("Hikari Pool was successfully configured and created");
        }

        return dataSource;
    }

    public static synchronized MyDataSource getInstance() {
        if (instance == null) {
            instance = new MyDataSource();
        }
        return instance;
    }

    private static Properties getProperties()  {
        try (InputStream propFile = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(APP_SETTINGS_FILE)) {
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

        hikariConfig.setJdbcUrl(props.getProperty(DB_URL));
        hikariConfig.setUsername(props.getProperty(DB_USERNAME));
        hikariConfig.setPassword(props.getProperty(DB_PASSWORD));
        hikariConfig.setDriverClassName(props.getProperty(DB_DRIVER));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(props.getProperty(DB_MAXIMUM_POOL_SIZE)));
//        hikariConfig.setLeakDetectionThreshold(Integer.parseInt(props.getProperty(SET_LEAK_DETECTION_THRESHOLD))); //for testing purposes only

        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit",
                props.getProperty(DB_CACHE_PREP_STMTS));
        hikariConfig.addDataSourceProperty("prepStmtCacheSize",
                props.getProperty(DB_PREP_STMT_CACHE_SIZE));

        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit",
                props.getProperty(DB_PREP_STMT_CACHE_SQL_LIMIT));

        log.info("Hikari Pool configuration was successfully created!");

        settings.put(MAX_GUESTS_NUMBER, props.getProperty(SETTINGS_MAX_GUESTS_NUMBER));

        return hikariConfig;
    }

    public static Map<String, String> getSettings() {
        return settings;
    }

}
