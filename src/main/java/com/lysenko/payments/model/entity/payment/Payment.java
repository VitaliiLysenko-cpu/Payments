package com.lysenko.payments.model.entity.payment;

import java.io.Serializable;
import java.util.Date;

public class Payment implements Serializable {
    private final int id;
    private final double amount;
    private final PaymentStatus status;
    private final Date dateCreated;

    public Payment(int id, PaymentStatus status, Date dateCreated, double amount) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.dateCreated = dateCreated;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
