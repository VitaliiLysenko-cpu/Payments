package com.lysenko.payments.model.payment;

import com.lysenko.payments.data_base.db.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.lysenko.payments.model.payment.PaymentStatus.*;

public class PaymentDao {
    public static final int ACCOUNTS_PER_PAGE = 3;
    private static final String ADD_NEW_PAYMENT = "INSERT INTO payment (status, date, amount, account_id) VALUES (?,?,?,?)";
    private static final String GET_PAYMENTS_COUNT = "SELECT COUNT(*) AS numberOfPayments FROM payment WHERE account_id = ?";
    private static final String GET_ACCOUNT_PAYMENT = "SELECT * FROM payment WHERE account_id = ? LIMIT ?,?";
    private final Connection connection = DBManager.getInstance().getConnection();

    public List<Payment> getPaymentForAccount(String accountId, int page) {
        int offset = page * ACCOUNTS_PER_PAGE - ACCOUNTS_PER_PAGE;
        try (PreparedStatement statement = connection.prepareStatement(GET_ACCOUNT_PAYMENT)) {
            statement.setString(1, accountId);
            statement.setInt(2, offset);
            statement.setInt(3, ACCOUNTS_PER_PAGE);
            ResultSet rs = statement.executeQuery();
            return resultSetToResult(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }
    public int getPaymentsCount(String accountId) {
        try (PreparedStatement pr = connection.prepareStatement(GET_PAYMENTS_COUNT)) {
            pr.setString(1, accountId);
            final ResultSet rs = pr.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    private List<Payment> resultSetToResult(ResultSet rs) throws SQLException {
        List<Payment> result = new ArrayList<>();
        while (rs.next()) {
            Payment payment = new Payment(
                    rs.getInt("id"),
                    rs.getString("status").equalsIgnoreCase("new") ? NEW : DONE,
                    rs.getDate("date"),
                    rs.getDouble("amount")
            );
            result.add(payment);
        }
        return result;
    }
    public void addNewPayment(double amount, int accountId){
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        try(PreparedStatement pr = connection.prepareStatement(ADD_NEW_PAYMENT)){
            pr.setString(1,"new");
            pr.setDate(2, sqlDate);
            pr.setDouble(3,amount);
            pr.setInt(4, accountId);
            pr.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
