package util;

import model.Account;
import model.Transaction;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private static final String ACCOUNT_FILE = "accounts";
    private static final String TRANSACTION_FILE = "transactions";

    // ✅ Đọc danh sách tài khoản (format: accNum,owner,password,pin,balance,bankName,locked)
    public static List<Account> loadAccounts() {
        List<Account> list = new ArrayList<>();
        File file = new File(ACCOUNT_FILE);
        if (!file.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] p = line.split(",");
                if (p.length < 5) {
                    System.out.println("⚠️ Dòng tài khoản không hợp lệ: " + line);
                    continue;
                }

                try {
                    String acc = p[0].trim();
                    String owner = p[1].trim();
                    String password = p[2].trim();
                    String pin = p[3].trim();
                    double balance = Double.parseDouble(p[4].trim());
                    String bank = (p.length >= 6) ? p[5].trim() : "DefaultBank";

                    // ✅ Nếu có cột locked thì đọc, còn không thì mặc định là false
                    boolean locked = false;
                    if (p.length >= 7) {
                        locked = Boolean.parseBoolean(p[6].trim());
                    }

                    list.add(new Account(acc, owner, password, pin, balance, bank, locked));

                } catch (Exception ex) {
                    System.out.println("❌ Lỗi khi đọc dòng tài khoản: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ✅ Ghi danh sách tài khoản ra file (có thêm cột locked)
    public static void saveAccounts(List<Account> accounts) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ACCOUNT_FILE))) {
            for (Account a : accounts) {
                bw.write(a.toString()); // format: accNum,owner,password,pin,balance,bankName,locked
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Đọc danh sách giao dịch (format: acc,type,amount,datetime)
    public static List<Transaction> loadTransactions() {
        List<Transaction> list = new ArrayList<>();
        File file = new File(TRANSACTION_FILE);
        if (!file.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                Transaction t = Transaction.fromDataString(line);
                if (t != null) {
                    list.add(t);
                    continue;
                }

                String[] p = line.split(",");
                if (p.length < 4) {
                    System.out.println("⚠️ Invalid transaction line: " + line);
                    continue;
                }

                String acc = p[0].trim();
                String type = p[1].trim();
                double amt;
                try {
                    amt = Double.parseDouble(p[2].trim());
                } catch (NumberFormatException nfe) {
                    System.out.println("⚠️ Invalid amount: " + line);
                    continue;
                }

                String timeRaw = p[3].trim();

                try {
                    LocalDateTime dt = LocalDateTime.parse(timeRaw, Transaction.FILE_FMT);
                    list.add(new Transaction(acc, type, amt, dt));
                } catch (Exception ex1) {
                    try {
                        LocalDateTime dtIso = LocalDateTime.parse(timeRaw);
                        list.add(new Transaction(acc, type, amt, dtIso));
                    } catch (Exception ex2) {
                        System.out.println("⚠️ Cannot parse datetime: " + line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Ghi danh sách giao dịch ra file
    public static void saveTransactions(List<Transaction> transactions) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TRANSACTION_FILE))) {
            for (Transaction t : transactions) {
                bw.write(t.toDataString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
