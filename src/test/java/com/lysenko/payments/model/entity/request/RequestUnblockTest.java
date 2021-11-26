package com.lysenko.payments.model.entity.request;

import org.junit.jupiter.api.*;

class RequestUnblockTest {
    private RequestUnblock requestUnblock;
    private int id = 1;
    private StatusRequest status;
    private int accountId;

    @BeforeEach
    void setUp() {
        requestUnblock = new RequestUnblock(
                id,
                status,
                accountId
        );
    }

    @Test
    void getIdTest() {
        int actual = requestUnblock.getId();
        Assertions.assertEquals(id, actual);
    }

    @Test
    void getStatusRequestTest() {
        StatusRequest actual = requestUnblock.getStatusRequest();
        Assertions.assertEquals(status, actual);
    }

    @Test
    void getAccountIdTest() {
        int actual = requestUnblock.getAccountId();
        Assertions.assertEquals(accountId, actual);
    }
}