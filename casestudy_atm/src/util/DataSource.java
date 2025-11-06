package util;

import model.Account;
import model.Transaction;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private static final String ACCOUNT_FILE = "accounts.txt";
    private static final String TRANSACTION_FILE = "transactions.txt";

    // Định dạng thời gian dùng khi đọc/ghi file: dd/MM/yyyy HH:mm (không có giây)
    private static final DateTimeFormatter FILE_TIME_FMT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // Đọc danh sách tài khoản
    public static List<Account> loadAccounts() {
        List<Account> list = new ArrayList<>();
        File file = new File(ACCOUNT_FILE);
        if (!file.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length == 4) {
                    // p[0]=accNum, p[1]=owner, p[2]=pin, p[3]=balance
                    try {
                        double bal = Double.parseDouble(p[3]);
                        list.add(new Account(p[0], p[1], p[2], bal));
                    } catch (NumberFormatException nfe) {
                        System.out.println("⚠ Bỏ qua dòng account không hợp lệ: " + line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Ghi danh sách tài khoản ra file
    public static void saveAccounts(List<Account> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ACCOUNT_FILE))) {
            for (Account a : list) {
                bw.write(String.format("%s,%s,%s,%.2f",
                        a.getAccountNumber(), a.getOwnerName(), a.getPin(), a.getBalance()));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Đọc danh sách giao dịch
    public static List<Transaction> loadTransactions() {
        List<Transaction> list = new ArrayList<>();
        File file = new File(TRANSACTION_FILE);
        if (!file.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length == 4) {
                    String acc = p[0];
                    String type = p[1];
                    double amount;
                    try {
                        amount = Double.parseDouble(p[2]);
                    } catch (NumberFormatException nfe) {
                        System.out.println("⚠ Bỏ qua dòng transaction không hợp lệ (số tiền): " + line);
                        continue;
                    }

                    String timeStr = p[3].trim();
                    try {
                        // parse với định dạng dd/MM/yyyy HH:mm
                        LocalDateTime time = LocalDateTime.parse(timeStr, FILE_TIME_FMT);
                        // dùng constructor có thời gian
                        Transaction t = new Transaction(acc, type, amount, time);
                        list.add(t);
                    } catch (Exception ex) {
                        System.out.println("⚠ Không parse được thời gian cho dòng: " + line);
                        // nếu muốn, thử parse ISO fallback:
                        try {
                            LocalDateTime timeIso = LocalDateTime.parse(timeStr);
                            Transaction t = new Transaction(acc, type, amount, timeIso);
                            list.add(t);
                        } catch (Exception ex2) {
                            // bỏ qua dòng nếu không parse được
                            System.out.println("   -> Bỏ qua dòng transaction do định dạng thời gian lạ.");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Ghi danh sách giao dịch
    public static void saveTransactions(List<Transaction> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TRANSACTION_FILE))) {
            for (Transaction t : list) {
                // lưu thời gian theo định dạng FILE_TIME_FMT (dd/MM/yyyy HH:mm)
                String timeStr = t.getDateTime().format(FILE_TIME_FMT);
                bw.write(String.format("%s,%s,%.2f,%s",
                        t.getAccountNumber(), t.getType(), t.getAmount(), timeStr));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
