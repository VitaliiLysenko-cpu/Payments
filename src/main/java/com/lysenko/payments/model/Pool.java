package com.lysenko.payments.model;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class Pool {
    private static final String PROPERTIES_PATH = "/pool-conf.properties";
    private static Pool instance;
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
            //TODO add logger
            e.printStackTrace();
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
            //TODO add logger
            throwables.printStackTrace();
        }
        return null;
    }
}
