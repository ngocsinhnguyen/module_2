package service;

import model.Transaction;
import repository.TransactionRepository;

import java.util.Collections;
import java.util.List;

public class TransactionService {
    private final TransactionRepository repo;

    public TransactionService(TransactionRepository repo) {
        this.repo = repo;
    }

    public List<Transaction> getTransactionsByAccount(String accNum) {
        List<Transaction> list = repo.findByAccount(accNum);
        Collections.sort(list); // Transaction.compareTo => mới nhất trước
        return list;
    }
}
