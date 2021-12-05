package com.lysenko.payments.model.entity;

import java.io.Serializable;
import java.util.Date;

public class Card implements Serializable {
    private final int id; //todo revisit data type
    private final String number;
    private final Date expiration;
    private final int cvc;

    public Card(int id, String number, Date expiration, int cvc) {
        this.id = id;
        this.number = number;
        this.expiration = expiration;
        this.cvc = cvc;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", expiration='" + expiration + '\'' +
                ", cvc='" + cvc + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public Date getExpiration() {
        return expiration;
    }

    public int getCvc() {
        return cvc;
    }
}
