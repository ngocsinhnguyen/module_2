package model;

import java.time.LocalDateTime;

public class Transaction implements Comparable<Transaction> {
    private String accountNumber;
    private String type;
    private double amount;
    private LocalDateTime dateTime;

    public Transaction(String accountNumber, String type, double amount) {
        this(accountNumber, type, amount, LocalDateTime.now());
    }

    public Transaction(String accountNumber, String type, double amount, LocalDateTime dateTime) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: %.2f at %s",
                accountNumber, type, amount, dateTime);
    }

    // 👇 Thêm phần này để sort theo thời gian
    @Override
    public int compareTo(Transaction other) {
        return this.dateTime.compareTo(other.dateTime);
    }
}
