package com.lysenko.payments.utils;

import com.lysenko.payments.model.Pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RowsCounterInTable {
    public static int getCountBY(String param, String sqlRequest) {
        return CountBY(param, sqlRequest);
    }

    private static int CountBY(String param, String sqlRequest) {
        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(sqlRequest)) {
            pr.setString(1, param);
            final ResultSet rs = pr.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public static int getCountBY( String sqlRequest) {
        return CountBY(sqlRequest, sqlRequest);
    }
}
