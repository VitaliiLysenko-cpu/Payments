package com.lysenko.payments.model.dao;

import com.lysenko.payments.model.Pool;
import com.lysenko.payments.model.entity.request.RequestUnblock;
import com.lysenko.payments.model.entity.request.StatusRequest;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestUnblockDao {
    private static final String GET_ACCOUNTS_FOR_UNBLOCK = "SELECT * FROM request_unblock WHERE status = 'NEW'";
    private static final String CHANGE_REQUEST_STATUS = "UPDATE request_unblock SET status = 'DONE' WHERE account_id =?";

    private final Logger log = Logger.getLogger(RequestUnblockDao.class);

    public List<RequestUnblock> getAccountsToUnblock() {
        List<RequestUnblock> requestUnblocks = new ArrayList<>();
        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ACCOUNTS_FOR_UNBLOCK)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                requestUnblocks.add(create(rs));
            }
        } catch (SQLException throwables) {
            log.error("can not added element to requestUnblocks", throwables);
        }
        return requestUnblocks;
    }

    private RequestUnblock create(ResultSet rs) throws SQLException {
        StatusRequest statusEnum;
        int id = rs.getInt("id");
        if ("NEW".equals(rs.getString("status"))) {
            statusEnum = StatusRequest.NEW;
        } else {
            statusEnum = StatusRequest.DONE;
        }
        return new RequestUnblock(
                id,
                statusEnum,
                rs.getInt("account_id")
        );
    }

    public void changeRequestStatus(int accountId) {
        try (Connection connection = Pool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(CHANGE_REQUEST_STATUS)) {
            ps.setInt(1, accountId);
            ps.execute();
        } catch (SQLException throwables) {
            log.error("can not change request status", throwables);
        }
    }
}
