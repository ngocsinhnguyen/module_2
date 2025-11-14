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
        if (account != null && findByAccountNumberAndBank(account.getAccountNumber(), account.getBankName()) == null) {
            accounts.add(account);
        }
        saveAll();
    }

    public Account findByAccountNumberAndBank(String accNum, String bankName) {
        if (accNum == null || bankName == null || accNum.isEmpty() || bankName.isEmpty()) {
            return null;
        }
        for (Account a : accounts) {
            if (a.getAccountNumber().trim().equals(accNum.trim()) &&
                    a.getBankName().trim().equalsIgnoreCase(bankName.trim())) {

                return a;
            }
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
        Account a = findByAccountNumberAndBank(accNum, "TPBank");
        if (a != null && a.isLocked()) {
            a.setLocked(false);
            saveAll();
            return true;
        }
        return false;
    }
}
