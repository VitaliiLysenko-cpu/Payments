package com.lysenko.payments.model.entity.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class AccountTest {

    private Account account;
    private int id = 1;
    private  String name;
    private String number;
    private double balance;
    private Status status;

    @BeforeEach
    void  setup (){
        account = new Account(
                id,
                name,
                number,
                balance,
                status
        );
    }

    @Test
    void getIdTest() {
        int actual = account.getId();
        Assertions.assertEquals(id, actual);
    }

    @Test
    void getNameTest() {
        String actual = account.getName();
        Assertions.assertEquals(name, actual);
    }

    @Test
    void getNumberTest() {
        String actual = account.getNumber();
        Assertions.assertEquals(number, actual);
    }

    @Test
    void getBalanceTest() {
        double actual = account.getBalance();
        Assertions.assertEquals(balance, actual);
    }

    @Test
    void getStatusTest() {
        Status actual = account.getStatus();
        Assertions.assertEquals(status, actual);
    }

    @Test
    void testToString() {
        String actual = account.toString();
       String expected = "Account{" +
               "id= " + id +
               ", name= "  + name + '\'' +
               ", number= " + number + '\'' +
               ", balance= " + balance +
               '}';

       Assertions.assertEquals(actual, expected);
    }

}