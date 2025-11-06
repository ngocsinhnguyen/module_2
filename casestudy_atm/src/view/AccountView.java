package view;

import model.Account;

public class AccountView {
    public void showBalance(Account acc) {
        System.out.printf("Current Balance: %.2f\n", acc.getBalance());
    }
}
