package model;

public class Account {
    private String accountNumber;
    private String ownerName;
    private String pin;
    private double balance;

    public Account(String accountNumber, String ownerName, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public String getPin() { return pin; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (Balance: %.2f)", accountNumber, ownerName, balance);
    }
}
