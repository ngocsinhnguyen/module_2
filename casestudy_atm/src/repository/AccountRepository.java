package repository;

import model.Account;
import util.DataSource;

import java.util.List;

public class AccountRepository {
    private final List<Account> accounts;

    public AccountRepository() {
        accounts = DataSource.loadAccounts();
    }

    /**
     * Thêm tài khoản mới vào danh sách in-memory.
     * Lưu ý: Cần gọi saveAll() sau khi thêm để lưu vào DataSource nếu cần.
     */
    public void save(Account account) {
        if (account != null && findByAccountNumber(account.getAccountNumber()) == null) {
            accounts.add(account);
        }
    }

    public Account findByAccountNumber(String accNum) {
        if (accNum == null) return null;
        for (Account a : accounts) {
            if (a.getAccountNumber().trim().equals(accNum.trim())) return a;
        }
        return null;
    }

    public List<Account> getAll() {
        return accounts;
    }

    public void saveAll() {
        DataSource.saveAccounts(accounts);
    }

    // ✅ Mở khóa tài khoản
    public boolean unlockAccount(String accNum) {
        Account a = findByAccountNumber(accNum);
        if (a != null && a.isLocked()) {
            a.setLocked(false);
            saveAll();
            return true;
        }
        return false;
    }
}
