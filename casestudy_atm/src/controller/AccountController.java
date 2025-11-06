package controller;

import model.Account;
import repository.AccountRepository;
import repository.TransactionRepository;
import service.AccountService;
import service.TransactionService;
import view.AccountView;
import view.MenuView;
import view.TransactionView;

public class AccountController {
    private final MenuView menu = new MenuView();
    private final AccountView accView = new AccountView();
    private final TransactionView transView = new TransactionView();

    // Dùng cùng 1 instance repo cho toàn bộ controller & service
    private final AccountRepository accRepo;
    private final TransactionRepository transRepo;
    private final AccountService accService;
    private final TransactionService transService;

    private Account currentAcc;

    public AccountController() {
        this.accRepo = new AccountRepository();
        this.transRepo = new TransactionRepository();
        this.accService = new AccountService(accRepo, transRepo);
        this.transService = new TransactionService(transRepo);
    }

    public void run() {
        while (true) {
            switch (menu.mainMenu()) {
                case 1 -> handleLogin();
                case 0 -> {
                    System.out.println("👋 Thoát chương trình. Tạm biệt!");
                    return;
                }
                default -> System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        }
    }

    private void handleLogin() {
        String accNum = menu.input("Nhập số tài khoản: ");
        String pin = menu.input("Nhập mã PIN: ");
        currentAcc = accService.login(accNum, pin);
        if (currentAcc != null) {
            System.out.println("✅ Đăng nhập thành công!");
            handleAtmMenu();
        } else {
            System.out.println("❌ Sai tài khoản hoặc PIN!");
        }
    }

    private void handleAtmMenu() {
        while (true) {
            switch (menu.atmMenu()) {
                // === Sửa ở đây: gọi showBalance(Account) thay vì showBalance(double) ===
                case 1 -> accView.showBalance(currentAcc);

                case 2 -> {
                    double amt = menu.inputDouble("Nhập số tiền nạp: ");
                    if (accService.deposit(currentAcc, amt))
                        System.out.println("✅ Nạp tiền thành công!");
                    else
                        System.out.println("❌ Số tiền không hợp lệ!");
                }
                case 3 -> {
                    double amt = menu.inputDouble("Nhập số tiền rút: ");
                    if (accService.withdraw(currentAcc, amt))
                        System.out.println("✅ Rút tiền thành công!");
                    else
                        System.out.println("❌ Rút tiền thất bại (số dư không đủ hoặc số tiền không hợp lệ)!");
                }
                case 4 -> {
                    String target = menu.input("Nhập tài khoản nhận: ");
                    double amt = menu.inputDouble("Nhập số tiền chuyển: ");
                    if (accService.transfer(currentAcc, target, amt))
                        System.out.println("✅ Chuyển tiền thành công!");
                    else
                        System.out.println("❌ Chuyển tiền thất bại!");
                }

                // === Sửa ở đây: dùng getHistory(...) và showHistory(...) ===
                case 5 -> {
                    var list = transService.getHistory(currentAcc.getAccountNumber());
                    transView.showHistory(list);
                }

                case 0 -> {
                    System.out.println("👋 Đăng xuất thành công!");
                    currentAcc = null;
                    return;
                }
                default -> System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        }
    }
}
