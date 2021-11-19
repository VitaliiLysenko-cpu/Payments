package com.lysenko.payments.model.dao;

import com.lysenko.payments.DateUtil;
import com.lysenko.payments.NumberGenerator;
import com.lysenko.payments.model.Pool;
import com.lysenko.payments.model.entity.Card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDao {
    private static final String GET_ACCOUNT_CARD = "SELECT * FROM payment_card WHERE account_id = ?";
    private static final String ADD_USER_CARD = "INSERT INTO payment_card(account_id,card_number, expiration, cvc_code) " +
            "VALUES (?,?,?,?) ";
    private static final String CREATE_NEW_CARD = "INSERT INTO payment_card(card_number, expiration, cvc_code, account_id) VALUES (?, ?, ?, ?)";

    public void newCard(int accountId) {
        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(CREATE_NEW_CARD)) {
            ps.setLong(1, NumberGenerator.get16DigitsNumber());
            ps.setDate(2, DateUtil.getTodayInTwoYears());
            ps.setLong(3, NumberGenerator.get3DigitsNumber());
            ps.setInt(4, accountId);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Card> getGetAccountCard(String accountId) {

        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ACCOUNT_CARD)) {
            statement.setString(1, accountId);
            ResultSet rs = statement.executeQuery();
            return resultSetToResult(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }

    private List<Card> resultSetToResult(ResultSet rs) throws SQLException {
        List<Card> result = new ArrayList<>();
        while (rs.next()) {
            Card card = new Card(
                    rs.getInt("id"),
                    rs.getString("card_number"),
                    rs.getDate("expiration"),
                    rs.getInt("cvc_code")
            );
            result.add(card);
        }
        return result;
    }

    public void addCard(int userId, String cardNumber, String cvc, String expiration) {
        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(ADD_USER_CARD)) {
            ps.setInt(1, userId);
            ps.setString(2, cardNumber);
            //todo hash
            ps.setString(3, cvc);
            //todo hash

            ps.setString(4, expiration);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}