package com.lysenko.payments.data_base.db;

import java.sql.Connection;


//TODO refactor
public class DBManager {
    private static DBManager instance;
    private final Connection connection = Pool.getInstance().getConnection();

    public static DBManager getInstance() {
        if (instance == null)
            synchronized (Pool.class) {
                instance = new DBManager();
            }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
