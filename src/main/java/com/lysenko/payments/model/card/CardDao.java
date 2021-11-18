package com.lysenko.payments.model.card;

import com.lysenko.payments.data_base.db.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDao {
    private static final String GET_ACCOUNT_CARD = "SELECT * FROM payment_card WHERE account_id = ?";
    private static final String ADD_USER_CARD =  "INSERT INTO payment_card(account_id,card_number, expiration, cvc_code, pin_code) " +
            "VALUES (?,?,?,?,?) ";
    private final Connection connection = DBManager.getInstance().getConnection();

    public List<Card> getGetAccountCard(String accountId) {

        try (PreparedStatement statement = connection.prepareStatement(GET_ACCOUNT_CARD)) {
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
    public void addCard(int userId, String cardNumber,String cvc,String pin,String expiration){
        try(PreparedStatement ps = connection.prepareStatement(ADD_USER_CARD)){
            ps.setInt(1, userId);
            ps.setString(2,cardNumber);
            //todo hash
            ps.setString(3,cvc);
            //todo hash
            ps.setString(4,pin);
            //todo hash
            ps.setString(5,expiration);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}