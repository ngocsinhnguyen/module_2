package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction implements Comparable<Transaction> {
    private String accountNumber;
    private String type; // DEPOSIT, WITHDRAW, TRANSFER_IN, TRANSFER_OUT
    private double amount;
    private LocalDateTime dateTime;

    // format hiển thị file: dd/MM/yyyy HH:mm
    public static final DateTimeFormatter FILE_FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public static final DateTimeFormatter DISPLAY_FMT = FILE_FMT;

    public Transaction(String accountNumber, String type, double amount) {
        this(accountNumber, type, amount, LocalDateTime.now());
    }

    public Transaction(String accountNumber, String type, double amount, LocalDateTime dateTime) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    @Override
    public int compareTo(Transaction other) {
        // sắp xếp giảm dần (mới nhất trước)
        return other.dateTime.compareTo(this.dateTime);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: %.2f at %s",
                accountNumber, type, amount, dateTime.format(DISPLAY_FMT));
    }

    // Ghi ra file: acc,type,amount,dd/MM/yyyy HH:mm
    public String toDataString() {
        return String.format("%s,%s,%.2f,%s",
                accountNumber, type, amount, dateTime.format(FILE_FMT));
    }

    // Parse từ dòng file
    public static Transaction fromDataString(String line) {
        String[] p = line.split(",");
        if (p.length < 4) return null;
        try {
            String acc = p[0].trim();
            String type = p[1].trim();
            double amt = Double.parseDouble(p[2].trim());
            // phần thời gian có thể chứa dấu phẩy nếu owner name ... nhưng per format it's last
            String timeStr = p[3].trim();
            // If time contains comma because of future changes, rejoin; here we assume 4 parts
            LocalDateTime dt = LocalDateTime.parse(timeStr, FILE_FMT);
            return new Transaction(acc, type, amt, dt);
        } catch (Exception e) {
            return null;
        }
    }
}
