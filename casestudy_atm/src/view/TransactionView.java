package view;

import model.Transaction;
import java.util.List;

public class TransactionView {
    public void showHistory(List<Transaction> list) {
        System.out.println("=== Transaction History ===");
        for (Transaction t : list) {
            System.out.println(t);
        }
    }
}
