package view;

import model.Transaction;

import java.util.List;

public class TransactionView {
    public void showAll(List<Transaction> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Không có giao dịch.");
            return;
        }
        System.out.println("\n--- LỊCH SỬ GIAO DỊCH ---");
        for (Transaction t : list) {
            System.out.println(t);
        }
    }
}
