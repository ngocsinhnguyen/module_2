package service;

import model.Account;
import model.Transaction;
import repository.AccountRepository;
import repository.TransactionRepository;

import java.util.Random;

public class AccountService {
    private final AccountRepository accountRepo;
    private final TransactionRepository transRepo;

    public AccountService(AccountRepository accountRepo, TransactionRepository transRepo) {
        this.accountRepo = accountRepo;
        this.transRepo = transRepo;
    }

    public Account findAccount(String accNum) {
        return accountRepo.findByAccountNumberAndBank(accNum, "TPBank");
    }

    /**
     * Tạo tài khoản mới với password và PIN được BCrypt hóa.
     * @param ownerName Tên chủ tài khoản
     * @param password Mật khẩu đăng nhập
     * @param pin Mã PIN giao dịch
     * @param bankName Tên ngân hàng
     * @param cardType Loại thẻ
     * @return Account mới hoặc null nếu thất bại
     */
    public Account createNewAccount(String ownerName, String password, String pin, String bankName, String cardType) {
        // Kiểm tra tính hợp lệ cơ bản
        if (ownerName == null || ownerName.trim().isEmpty() || password == null || password.length() < 6 || pin == null || pin.length() != 4) {
            return null;
        }

        // Mã hóa Password và PIN bằng BCrypt
        String hashedPassword = SecurityUtil.hash(password);
        String hashedPin = SecurityUtil.hash(pin);

        // Tạo số tài khoản ngẫu nhiên duy nhất
        String newAccountNumber = generateUniqueAccountNumber();

        // Tạo đối tượng Account mới, lưu trữ giá trị đã mã hóa
        Account newAccount = new Account(
                newAccountNumber,
                ownerName,
                hashedPassword,
                hashedPin,
                0.0,
                bankName
        );

        // Lưu tài khoản mới vào Repository
        accountRepo.save(newAccount);
        return newAccount;
    }

    // Phương thức tạo số tài khoản ngẫu nhiên 10 chữ số
    private String generateUniqueAccountNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(10);

        // Generate 10 random digits
        for (int i = 0; i < 10; i++) {
            // Append a random digit (0-9)
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

    // Login bằng password
    public Account login(String accNum, String password) {
        Account a = accountRepo.findByAccountNumberAndBank(accNum, "TPBank");
        if (a == null) return null;

        // Kiểm tra xem tài khoản có bị khóa không
        if (a.isLocked()) {
            System.out.println("🚫 Tài khoản đã bị khóa! Vui lòng liên hệ ngân hàng để mở khóa.");
            return null;
        }

        // Kiểm tra mật khẩu bằng BCrypt
        if (SecurityUtil.verify(password, a.getPassword())) {
            return a;
        }

        return null;
    }

    // Xác minh PIN
    public boolean verifyPin(Account acc, String pin) {
        if (acc == null || pin == null) return false;
        // Kiểm tra PIN bằng BCrypt
        return SecurityUtil.verify(pin, acc.getPin());
    }

    // Đổi PIN
    public boolean changePin(Account acc, String oldPin, String newPin) {
        if (acc == null) return false;
        // Xác minh PIN cũ
        if (!verifyPin(acc, oldPin)) return false;

        // Mã hóa PIN mới trước khi lưu
        String hashedNewPin = SecurityUtil.hash(newPin);
        acc.setPin(hashedNewPin);

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

    public boolean transfer(Account from, String targetAccNum, String receiverBank, double amount) {
        if (from == null || amount <= 0) return false;
        Account to = accountRepo.findByAccountNumberAndBank(targetAccNum, receiverBank);
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
        Account acc = accountRepo.findByAccountNumberAndBank(accNum, "TPBank");
        if (acc != null) {
            acc.setLocked(true);
            accountRepo.saveAll();
        }
    }
}