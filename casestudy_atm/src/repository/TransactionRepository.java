package repository;

import model.Transaction;
import util.DataSource;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private final List<Transaction> transactions;

    public TransactionRepository() {
        transactions = DataSource.loadTransactions();
    }

    public void add(Transaction t) {
        transactions.add(t);
        DataSource.saveTransactions(transactions);
    }

    public List<Transaction> findByAccount(String accNum) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAccountNumber().trim().equals(accNum.trim())) result.add(t);
        }
        return result;
    }

    public List<Transaction> getAll() {
        return transactions;
    }
}
