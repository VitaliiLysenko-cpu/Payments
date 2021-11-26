package com.lysenko.payments.model.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

class CardTest {
    private Card card;
    private int id = 1;
    private String number;
    private Date expiration;
    private int cvc;

    @BeforeEach
    void setUp() {
       card = new Card(
               id,
               number,
               expiration,
               cvc
       );
    }

    @Test
    void testToStringTest() {
       String actual = card.toString();
       String expected = "Card{" +
               "id=" + id +
               ", number='" + number + '\'' +
               ", expiration='" + expiration + '\'' +
               ", cvc='" + cvc + '\'' +
               '}';
       Assertions.assertEquals(expected, actual);
    }

    @Test
    void getIdTest() {
       int actual = card.getId();
       Assertions.assertEquals(id, actual);
    }

    @Test
    void getNumberTest() {
       String actual = card.getNumber();
       Assertions.assertEquals(number, actual);
    }

    @Test
    void getExpirationTest() {
       Date actual = card.getExpiration();
       Assertions.assertEquals(expiration, actual);
    }

    @Test
    void getCvcTest() {
       int actual = card.getCvc();
       Assertions.assertEquals(cvc, actual);
    }
}