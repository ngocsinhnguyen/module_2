package view;

import model.Account;

public class AccountView {
    public void showBalance(Account acc) {
        if (acc == null) {
            System.out.println("Không có tài khoản.");
            return;
        }
        System.out.printf("Số tài khoản: %s | Chủ sở hữu: %s | Số dư: %.2f VND \n",
                acc.getAccountNumber(), acc.getOwnerName(), acc.getBalance());
    }
}
