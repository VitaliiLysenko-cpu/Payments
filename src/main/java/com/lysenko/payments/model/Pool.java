package com.lysenko.payments.model;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class Pool {
    private static final String PROPERTIES_PATH = "/pool_conf.properties";
    private static Pool instance;
    private final Logger log = Logger.getLogger(Pool.class);
    private final BasicDataSource ds = new BasicDataSource();

    private Pool() {
        try {
            Properties properties = new Properties();
            properties.load(Pool.class.getResourceAsStream(PROPERTIES_PATH));
            ds.setDriverClassName(properties.getProperty("db.driver"));
            ds.setUrl(properties.getProperty("db.url"));
            ds.setUsername(properties.getProperty("db.user"));
            ds.setPassword(properties.getProperty("db.password"));
        } catch (IOException e) {
            log.error("can not get data from file ", e);
        }
    }

    public static synchronized Pool getInstance() {
        if (instance == null) {
            instance = new Pool();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException throwables) {
            log.error("can not get connection");
        }
        return null;
    }
}
