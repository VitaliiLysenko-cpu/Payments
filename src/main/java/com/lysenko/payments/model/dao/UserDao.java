package com.lysenko.payments.model.dao;

import com.lysenko.payments.model.Pool;
import com.lysenko.payments.model.entity.user.Role;
import com.lysenko.payments.model.entity.user.User;
import com.lysenko.payments.model.entity.user.UserStatus;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDao {
    public static final Integer USERS_PER_PAGE = 3;
    private static final String CHECK_USER_EMAIL = "SELECT * FROM user WHERE email = ?";
    private static final String CHANGE_USER_STATUS = "UPDATE user SET status = ? WHERE id = ?";
    private static final String GET_USER = "SELECT * FROM user WHERE email = ? AND password = ?";
    private static final String GET_USERS = "SELECT * FROM user WHERE role = 'user' LIMIT ?,?";
    private static final String CREATE_NEW_USER = "INSERT INTO user (email," +
            "name,surname,phone_num,password)VALUES (?,?,?,?,?)";
    private static final String GET_USERS_COUNT = "SELECT COUNT(*) AS numberOfUsers FROM user WHERE role = ?";
    private static final String CREATE_NEW_USER_FROM_ADMIN = "INSERT INTO user (email," +
            "name,surname,phone_num,password,role)VALUES (?,?,?,?,?,?)";

    private final Logger log = Logger.getLogger(UserDao.class);
    public int getUsersCount() {
        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_USERS_COUNT)) {
            ps.setString(1, Role.USER.name());
            final ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException throwables) {
            log.error("can not to get user count", throwables);
        }
        return 0;
    }

    public User logIn(String login, String password) {
        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USER)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return createUser(resultSet);
        } catch (SQLException throwables) {
            log.error("Login error", throwables);
        }
        return null;
    }

    public boolean registration(String email, String firstname, String lastname, String phoneNum, String password) {
        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(CHECK_USER_EMAIL)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                try (PreparedStatement statement = connection.prepareStatement(CREATE_NEW_USER,
                        Statement.RETURN_GENERATED_KEYS)) {
                    statement.setString(1, email);
                    statement.setString(2, firstname);
                    statement.setString(3, lastname);
                    statement.setString(4, phoneNum);
                    statement.setString(5, password);
                    //todo hash
                    statement.execute();
                    final ResultSet generatedKeys = statement.getGeneratedKeys();
                    generatedKeys.next();
                    AccountDao accountDao = new AccountDao();
                    accountDao.createAccount(generatedKeys.getInt(1));
                }
            }else{
                log.debug("this email already using");
                 return false;
            }
        } catch (SQLException throwables) {
            log.error("account was not created", throwables);
        }
        return true;
    }
    public boolean registration(String email, String firstname, String lastname, String phoneNum, String password,String role) {
        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(CHECK_USER_EMAIL)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                try (PreparedStatement statement = connection.prepareStatement(CREATE_NEW_USER_FROM_ADMIN,
                        Statement.RETURN_GENERATED_KEYS)) {
                    statement.setString(1, email);
                    statement.setString(2, firstname);
                    statement.setString(3, lastname);
                    statement.setString(4, phoneNum);
                    statement.setString(5, password);
                    statement.setString(6, role);
                    statement.execute();
                }
            }else{
                log.debug("this email already using");
                 return false;
            }
        } catch (SQLException throwables) {
            log.error("account was not created", throwables);
        }
        return true;
    }

    private User createUser(ResultSet rs) throws SQLException {
        rs.next();
        int id = rs.getInt("id");
        String password = rs.getString("password"); //TODO maybe remove
        String email = rs.getString("email");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone_num");
        Role role = rs.getString("role")
                .equalsIgnoreCase("user") ? Role.USER : Role.ADMIN;
        UserStatus status = rs.getString("status")
                .equalsIgnoreCase("unblocked") ? UserStatus.UNBLOCKED : UserStatus.BLOCKED;

        return new User(id, password, email, name, surname, phone, role, status);
    }

    public List<User> getUserDao(int page) {
        int offset = page * USERS_PER_PAGE - USERS_PER_PAGE;
        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_USERS)) {
            ps.setInt(1, offset);
            ps.setInt(2, USERS_PER_PAGE);
            ResultSet rs = ps.executeQuery();
            List<User> users = new ArrayList<>();
            UserStatus userStatus;
            Role role = Role.USER;
            while (rs.next()) {
                int id = rs.getInt("id");
                if ("BLOCKED".equals(rs.getString("status"))) {
                    userStatus = UserStatus.BLOCKED;
                } else {
                    userStatus = UserStatus.UNBLOCKED;
                }
                User user = new User(
                        id,
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("phone_num"),
                        role,
                        userStatus
                );
                users.add(user);
            }
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }

    public void toBlockUser(UserStatus action, String userId) {
        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(CHANGE_USER_STATUS)) {
            ps.setString(1, action.name());
            ps.setString(2, userId);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
