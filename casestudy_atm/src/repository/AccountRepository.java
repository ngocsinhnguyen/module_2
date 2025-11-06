package repository;

import model.Account;
import util.DataSource;
import java.util.List;

public class AccountRepository {
    private final List<Account> accounts;

    public AccountRepository() {
        accounts = DataSource.loadAccounts();
    }

    public Account findByAccountNumber(String accNum) {
        for (Account a : accounts) {
            if (a.getAccountNumber().equals(accNum)) return a;
        }
        return null;
    }

    public List<Account> getAll() {
        return accounts;
    }

    public void saveAll() {
        DataSource.saveAccounts(accounts);
    }
}
