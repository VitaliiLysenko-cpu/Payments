package com.lysenko.payments.model.dao;

import com.lysenko.payments.model.Pool;
import com.lysenko.payments.model.entity.account.Account;
import com.lysenko.payments.model.entity.account.MarkChangeBalance;
import com.lysenko.payments.model.entity.account.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountDao {

    public static final String SENT_REQUEST_TO_UNBLOCK = "INSERT INTO request_unblock(account_id) VALUES (?)";
    public static final String CHANGE_STATUS_ACCOUNT = "UPDATE account SET status = ? WHERE id = ?";
    public static final String GET_BALANCE_FROM_ACCOUNT = "SELECT balance FROM account WHERE id = ?";
    public static final String CHANGE_BALANCE_FOR_ACCOUNT = "UPDATE account SET balance = ? WHERE id = ?";
    public static final String GET_USER_ACCOUNTS = "SELECT * FROM account WHERE user_id = ?";
    public static final String BALANCE = "balance";
    private static final String REQUEST_FOR_CREATE_NEW_ACCOUNT = "INSERT INTO create_account_request(userId) VALUE (?)";

    public List<Account> getUserAccounts(int userId) {
        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USER_ACCOUNTS)) {
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            return resultSetToAccounts(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }

    public void requestForCreateNewAccount(String userId) {
        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(REQUEST_FOR_CREATE_NEW_ACCOUNT)) {
            ps.setString(1, userId);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private List<Account> resultSetToAccounts(ResultSet rs) throws SQLException {
        List<Account> result = new ArrayList<>();
        Status statusEnum;
        while (rs.next()) {
            int id = rs.getInt("id");
            if ("BLOCKED".equals(rs.getString("status"))) {
                statusEnum = Status.BLOCKED;
            } else {
                statusEnum = Status.OPEN;
            }
            Account account = new Account(
                    id,
                    rs.getString("name"),
                    rs.getString("number"),
                    rs.getDouble(BALANCE),
                    statusEnum
            );
            result.add(account);
        }
        return result;
    }

    public void changeBalance(double total, int accountId, MarkChangeBalance mark) {
        try(Connection connection = Pool.getInstance().getConnection()) {
            changeBalance(total, accountId, mark, connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void changeBalance(double total, int accountId, MarkChangeBalance mark, Connection connection) {
        try (
             PreparedStatement getBalance = connection.prepareStatement(GET_BALANCE_FROM_ACCOUNT);
             PreparedStatement changeBalanceStatement = connection.prepareStatement(CHANGE_BALANCE_FOR_ACCOUNT)
        ) {
            getBalance.setInt(1, accountId);
            ResultSet rs = getBalance.executeQuery();
            rs.next();
            double balance = rs.getDouble(BALANCE);
            if (mark.equals(MarkChangeBalance.MINUS)) {
                total = total * -1;
            }
            changeBalanceStatement.setDouble(1, balance + total);
            changeBalanceStatement.setInt(2, accountId);
            changeBalanceStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void makePayment(double total, int accountId) {
        PaymentDao paymentDao = new PaymentDao();
        Connection connection = Pool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            changeBalance(total, accountId, MarkChangeBalance.MINUS, connection);
            paymentDao.addNewPayment(total, accountId, connection);
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public double getAccountBalance(String accountId) {
        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement getBalance = connection.prepareStatement(GET_BALANCE_FROM_ACCOUNT)) {
            getBalance.setString(1, accountId);
            ResultSet rs = getBalance.executeQuery();
            rs.next();
            return rs.getDouble(BALANCE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void toBlockAccount(Status action, int accountId) {
        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement changeStatus = connection.prepareStatement(CHANGE_STATUS_ACCOUNT)) {
            changeStatus.setString(1, action.name());
            changeStatus.setInt(2, accountId);
            changeStatus.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void toSentRequest(int accountId) {
        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement sentRequest = connection.prepareStatement(SENT_REQUEST_TO_UNBLOCK)) {
            sentRequest.setInt(1, accountId);
            sentRequest.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
