package com.lysenko.payments.model.card;

import java.io.Serializable;
import java.util.Date;

public class Card implements Serializable {
    private int id; //todo revisit data type
    private String number;
    private Date expiration;
    private int cvc;

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

    public Card(int id, String number, Date expiration, int cvc) {
        this.id = id;
        this.number = number;
        this.expiration = expiration;
        this.cvc = cvc;
    }
}
