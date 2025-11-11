package service;

import model.Account;
import model.Transaction;
import repository.AccountRepository;
import repository.TransactionRepository;
import java.util.UUID;

public class AccountService {
    private final AccountRepository accountRepo;
    private final TransactionRepository transRepo;

    public AccountService(AccountRepository accountRepo, TransactionRepository transRepo) {
        this.accountRepo = accountRepo;
        this.transRepo = transRepo;
    }

    public Account findAccount(String accNum) {
        return accountRepo.findByAccountNumber(accNum);
    }

    /**
     * Tạo tài khoản mới với password và PIN riêng biệt
     * @param ownerName Tên chủ tài khoản
     * @param password Mật khẩu đăng nhập
     * @param pin Mã PIN giao dịch
     * @param bankName Tên ngân hàng
     * @return Account mới hoặc null nếu thất bại
     */
    public Account createNewAccount(String ownerName, String password, String pin, String bankName, String cardType) {
        // Kiểm tra tính hợp lệ
        if (ownerName == null || ownerName.trim().isEmpty()) {
            return null;
        }
        if (password == null || password.length() < 6) {
            return null;
        }
        if (pin == null || pin.length() != 4) {
            return null;
        }

        // Tạo số tài khoản ngẫu nhiên duy nhất
        String newAccountNumber = generateUniqueAccountNumber();

        // Tạo đối tượng Account mới với đầy đủ tham số
        Account newAccount = new Account(
                newAccountNumber,   // Số tài khoản
                ownerName,          // Tên chủ tài khoản
                password,           // Password đăng nhập
                pin,                // PIN giao dịch
                0.0,                // Số dư ban đầu = 0
                bankName            // Tên ngân hàng
        );

        // Lưu tài khoản mới vào Repository
        accountRepo.save(newAccount);
        return newAccount;
    }

    // Phương thức tạo số tài khoản ngẫu nhiên duy nhất
    private String generateUniqueAccountNumber() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
    }

    // Login bằng password
    public Account login(String accNum, String password) {
        Account a = accountRepo.findByAccountNumber(accNum);
        if (a == null) return null;

        // Kiểm tra xem tài khoản có bị khóa không
        if (a.isLocked()) {
            System.out.println("🚫 Tài khoản đã bị khóa! Vui lòng liên hệ ngân hàng để mở khóa.");
            return null;
        }

        // Kiểm tra mật khẩu
        if (a.getPassword().equals(password)) {
            return a;
        }

        return null;
    }

    public boolean verifyPin(Account acc, String pin) {
        if (acc == null || pin == null) return false;
        return acc.getPin().equals(pin);
    }

    public boolean changePin(Account acc, String oldPin, String newPin) {
        if (acc == null) return false;
        if (!verifyPin(acc, oldPin)) return false;
        acc.setPin(newPin);
        accountRepo.saveAll();
        return true;
    }

    public boolean deposit(Account acc, double amount) {
        if (acc == null || amount <= 0) return false;
        acc.deposit(amount);
        transRepo.add(new Transaction(acc.getAccountNumber(), "Nạp tiền", amount));
        accountRepo.saveAll();
        return true;
    }

    public boolean withdraw(Account acc, double amount) {
        if (acc == null || amount <= 0) return false;
        if (!acc.withdraw(amount)) return false;
        transRepo.add(new Transaction(acc.getAccountNumber(), "Rút tiền", amount));
        accountRepo.saveAll();
        return true;
    }

    public boolean transfer(Account from, String targetAccNum, double amount) {
        if (from == null || amount <= 0) return false;
        Account to = accountRepo.findByAccountNumber(targetAccNum);
        if (to == null) return false;
        if (!from.withdraw(amount)) return false;
        to.deposit(amount);
        transRepo.add(new Transaction(from.getAccountNumber(), "Tiền đi", amount));
        transRepo.add(new Transaction(to.getAccountNumber(), "Tiền đến", amount));
        accountRepo.saveAll();
        return true;
    }

    public double getBalance(Account acc) {
        return (acc == null) ? 0.0 : acc.getBalance();
    }

    public void lockAccount(String accNum) {
        Account acc = accountRepo.findByAccountNumber(accNum);
        if (acc != null) {
            acc.setLocked(true);
            accountRepo.saveAll();
        }
    }
}