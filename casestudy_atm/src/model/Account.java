package model;

public class Account {
    private String accountNumber;
    private String ownerName;
    private String password;
    private String pin;
    private double balance;
    private String bankName;
    private boolean locked; // ✅ thêm trạng thái khóa tài khoản

    // ✅ constructor chính (mặc định locked = false)
    public Account(String accountNumber, String ownerName, String password, String pin, double balance, String bankName) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.password = password;
        this.pin = pin;
        this.balance = balance;
        this.bankName = bankName;
        this.locked = false; // mặc định tài khoản chưa bị khóa
    }

    // ✅ constructor có thêm locked (dành cho đọc file có cột locked)
    public Account(String accountNumber, String ownerName, String password, String pin, double balance, String bankName, boolean locked) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.password = password;
        this.pin = pin;
        this.balance = balance;
        this.bankName = bankName;
        this.locked = locked;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPin() {
        return pin;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    // ✅ Getter/Setter cho trạng thái khóa
    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    // --- Giao dịch ---
    public void deposit(double amount) {
        if (amount > 0) this.balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    // ✅ Cập nhật toString để lưu thêm trạng thái locked vào file
    @Override
    public String toString() {
        // định dạng: accNum,owner,password,pin,balance,bankName,locked
        return String.format("%s,%s,%s,%s,%.2f,%s,%s",
                accountNumber, ownerName, password, pin, balance, bankName, locked);
    }
}
