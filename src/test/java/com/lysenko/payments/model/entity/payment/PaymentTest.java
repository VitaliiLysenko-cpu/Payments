package com.lysenko.payments.model.entity.payment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;



class PaymentTest {

    private Payment payment;
    private int id = 1;
    private double amount;
    private PaymentStatus status;
    private Date dateCreated;

    @BeforeEach
    void setUp() {
        payment= new Payment(
                id,
                status,
                dateCreated,
                amount
        );


    }

    @Test
    void getIdTest() {
        int actual = payment.getId();
        Assertions.assertEquals(id, actual);
    }

    @Test
    void getAmountTest() {
        double actual = payment.getAmount();
        Assertions.assertEquals(amount, actual);
    }

    @Test
    void getStatusTest() {
        PaymentStatus actual = payment.getStatus();
        Assertions.assertEquals(status, actual);
    }

    @Test
    void getDateCreatedTest() {
        Date actual = payment.getDateCreated();
        Assertions.assertEquals(dateCreated,actual);
    }

    @Test
    void testToString() {
        String actual = payment.toString();
        String expected = "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
        Assertions.assertEquals(expected, actual);
    }
}