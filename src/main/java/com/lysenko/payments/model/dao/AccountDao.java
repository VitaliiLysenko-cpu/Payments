package com.lysenko.payments.model.dao;

import com.lysenko.payments.NumberGenerator;
import com.lysenko.payments.model.Pool;
import com.lysenko.payments.model.entity.account.Account;
import com.lysenko.payments.model.entity.account.MarkChangeBalance;
import com.lysenko.payments.model.entity.account.Status;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.lysenko.payments.model.dao.PaymentDao.ACCOUNTS_PER_PAGE;

public class AccountDao {

    public static final String SENT_REQUEST_TO_UNBLOCK = "INSERT INTO request_unblock(account_id) VALUES (?)";
    public static final String CHECK_REQUEST_TO_UNBLOCK_BY_ACCOUNT_ID = "SELECT account_id FROM request_unblock WHERE status='NEW' AND account_id =?";
    public static final String CHANGE_STATUS_ACCOUNT = "UPDATE account SET status = ? WHERE id = ?";
    public static final String GET_BALANCE_FROM_ACCOUNT = "SELECT balance FROM account WHERE id = ?";
    public static final String CHANGE_BALANCE_FOR_ACCOUNT = "UPDATE account SET balance = ? WHERE id = ?";
    public static final String GET_USER_ACCOUNTS_LIMIT = "SELECT * FROM account WHERE user_id = ? ORDER BY %s %s  LIMIT ?,?";
    public static final String GET_USER_ACCOUNTS = "SELECT * FROM account WHERE user_id = ? LIMIT ?,?";
    public static final String BALANCE = "balance";
    public static final int ACCOUNT_GET_PAGE = 3;
    private static final String GET_USER_OPEN_ACCOUNTS = "SELECT * FROM account WHERE user_id = ? AND status = 'OPEN'";
    private static final String CREATE_NEW_ACCOUNT = "INSERT INTO account ( name, number, user_id) VALUES(?,?,?)";
    private static final String GET_ACCOUNTS_COUNT = "SELECT COUNT(*) AS numberOfAccounts FROM account WHERE user_id = ?";
    private final Logger log = Logger.getLogger(AccountDao.class);

    private static List<Account> resultSetToAccounts(ResultSet rs) throws SQLException {
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

    public List<Account> getAllUserAccounts(int userId, int page, String columnName, String sortOrder) {
        String sql = String.format(GET_USER_ACCOUNTS_LIMIT, columnName, sortOrder);
        int offset = page * ACCOUNTS_PER_PAGE - ACCOUNTS_PER_PAGE;
        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, offset);
            statement.setInt(3, ACCOUNTS_PER_PAGE);
            ResultSet rs = statement.executeQuery();
            return resultSetToAccounts(rs);
        } catch (SQLException throwables) {
            log.error("was can not get a list of accounts");
        }
        return Collections.emptyList();
    }


    public List<Account> getAllUserAccounts(int userId, int page) {
        int offset = page * ACCOUNTS_PER_PAGE - ACCOUNTS_PER_PAGE;
        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USER_ACCOUNTS)) {
            statement.setInt(1, userId);
            statement.setInt(2, offset);
            statement.setInt(3, ACCOUNTS_PER_PAGE);
            ResultSet rs = statement.executeQuery();
            return resultSetToAccounts(rs);
        } catch (SQLException throwables) {
            log.error("can not to get accounts", throwables);
        }
        return Collections.emptyList();
    }

    public List<Account> getUserOpenAccounts(int userId) {
        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USER_OPEN_ACCOUNTS)) {
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            return resultSetToAccounts(rs);
        } catch (SQLException throwables) {
            log.error("can not to get accounts", throwables);
        }
        return Collections.emptyList();
    }

    public int getAccountsCount(int userId) {
        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ACCOUNTS_COUNT)) {
            ps.setInt(1, userId);
            final ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public void changeBalance(double total, int accountId, MarkChangeBalance mark) {
        try (Connection connection = Pool.getInstance().getConnection()) {
            changeBalance(total, accountId, mark, connection);
        } catch (SQLException throwables) {
            log.error("Can not change balance", throwables);
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

    public void toChangeStatusAccount(Status action, int accountId) {
        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement changeStatus = connection.prepareStatement(CHANGE_STATUS_ACCOUNT)) {
            changeStatus.setString(1, action.name());
            changeStatus.setInt(2, accountId);
            changeStatus.execute();

        } catch (SQLException throwables) {
            log.error("Can not change status account", throwables);
        }
    }

    public boolean toCheckRequestWithAccountId(int accountId) {
        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement sentRequest = connection.prepareStatement(CHECK_REQUEST_TO_UNBLOCK_BY_ACCOUNT_ID)) {
            sentRequest.setInt(1, accountId);
            ResultSet rs = sentRequest.executeQuery();
            if (!rs.next()) {
                toSentRequest(accountId);
                return true;
            }
        } catch (SQLException throwables) {
            log.error("Can not to found request", throwables);
        }
        return false;
    }

    public void toSentRequest(int accountId) {
        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement sentRequest = connection.prepareStatement(SENT_REQUEST_TO_UNBLOCK)) {
            sentRequest.setInt(1, accountId);
            sentRequest.execute();
        } catch (SQLException throwables) {
            log.error("Can not to sent request", throwables);
        }
    }

    public void createAccount(int userId) {
        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(CREATE_NEW_ACCOUNT, Statement.RETURN_GENERATED_KEYS)) {
            Long number = NumberGenerator.get16DigitsNumber();
            ps.setString(1, "Account: " + number);
            ps.setString(2, number.toString());
            ps.setInt(3, userId);
            ps.execute();
            final ResultSet generatedKeys = ps.getGeneratedKeys();
            generatedKeys.next();
            CardDao cardDao = new CardDao();
            cardDao.newCard(generatedKeys.getInt(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
