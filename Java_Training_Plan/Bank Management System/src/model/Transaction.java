package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {
    private String type;
    private double amount;
    private double balance;
    private LocalDateTime time;

    public Transaction(String type, double amount, double balance) {
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.time = LocalDateTime.now();
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String toString() {
        return time + " | " + type + " | ₹" + amount +
                " | Balance: ₹" + balance;

    }
}
