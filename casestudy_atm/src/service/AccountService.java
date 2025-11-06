package service;

import model.Account;
import model.Transaction;
import repository.AccountRepository;
import repository.TransactionRepository;

public class AccountService {
    private final AccountRepository accountRepo;
    private final TransactionRepository transRepo;

    public AccountService(AccountRepository accountRepo, TransactionRepository transRepo) {
        this.accountRepo = accountRepo;
        this.transRepo = transRepo;
    }

    public Account login(String accNum, String pin) {
        Account acc = accountRepo.findByAccountNumber(accNum);
        if (acc != null && acc.getPin().equals(pin)) {
            return acc;
        }
        return null;
    }

    public boolean deposit(Account acc, double amount) {
        if (amount <= 0) return false;
        acc.deposit(amount);
        transRepo.add(new Transaction(acc.getAccountNumber(), "DEPOSIT", amount));
        accountRepo.saveAll();
        return true;
    }

    public boolean withdraw(Account acc, double amount) {
        if (amount <= 0 || amount > acc.getBalance()) return false;
        acc.withdraw(amount);
        transRepo.add(new Transaction(acc.getAccountNumber(), "WITHDRAW", amount));
        accountRepo.saveAll();
        return true;
    }

    // ✅ Cập nhật: xử lý toàn bộ logic transfer trong service
    public boolean transfer(Account from, String targetAccNum, double amount) {
        if (amount <= 0) return false;
        Account to = accountRepo.findByAccountNumber(targetAccNum);
        if (to == null) {
            System.out.println("❌ Không tìm thấy tài khoản nhận!");
            return false;
        }
        if (from.withdraw(amount)) {
            to.deposit(amount);
            transRepo.add(new Transaction(from.getAccountNumber(), "TRANSFER_OUT", amount));
            transRepo.add(new Transaction(to.getAccountNumber(), "TRANSFER_IN", amount));
            accountRepo.saveAll();
            return true;
        }
        System.out.println("❌ Số dư không đủ để chuyển!");
        return false;
    }

    public double getBalance(Account acc) {
        return acc.getBalance();
    }
}
