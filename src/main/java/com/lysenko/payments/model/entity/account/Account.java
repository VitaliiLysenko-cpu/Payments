package com.lysenko.payments.model.entity.account;

public class Account {

    private final int id;
    private final String name;
    private final String number;
    private final double balance;
    private final Status status ;

    public Account(int id, String name, String number, double balance, Status status) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.balance = balance;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public double getBalance() {
        return balance;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id= " + id +
                ", name= "  + name + '\'' +
                ", number= " + number + '\'' +
                ", balance= " + balance +
                '}';
    }
}
