package controller;

import model.Account;
import model.Transaction;
import repository.AccountRepository;
import repository.TransactionRepository;
import service.AccountService;
import service.TransactionService;
import view.AccountView;
import view.MenuView;
import view.TransactionView;

import java.util.List;

public class AccountController {
    private final MenuView menu = new MenuView();
    private final AccountView accView = new AccountView();
    private final TransactionView transView = new TransactionView();

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
            int main = menu.mainMenu();
            if (main == 1) {
                handleLogin();
            } else if (main == 2) {
                handleCreateNewTPBankCard();
            } else if (main == 0) {
                System.out.println("Bye!");
                return;
            } else {
                System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }

    /// ======================== TẠO THẺ MỚI TPBANK (MÔ PHỎNG LIVEBANK) ========================
    private void handleCreateNewTPBankCard() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   TẠO THẺ TPBANK MỚI TẠI LIVEBANK     ║");
        System.out.println("╚════════════════════════════════════════╝");

        // 1. Chọn loại thẻ với thông tin ưu đãi
        System.out.println("\n--- CHỌN LOẠI THẺ ---");
        System.out.println();

        System.out.println("1️⃣  Thẻ ATM Smart 24/7");
        System.out.println("    • Miễn phí rút tiền tại tất cả ATM TPBank");
        System.out.println("    • Miễn phí chuyển khoản nội bộ 24/7");
        System.out.println();

        System.out.println("2️⃣  Thẻ TPBank Visa CashFree");
        System.out.println("    • Hoàn tiền 0.5% mọi giao dịch");
        System.out.println("    • Miễn phí rút tiền mặt tại ATM");
        System.out.println("    • Ưu đãi mua sắm trực tuyến");
        System.out.println();

        System.out.println("3️⃣  Thẻ TPBank Visa Platinum");
        System.out.println("    • Hoàn tiền lên đến 2% cho mọi giao dịch");
        System.out.println("    • Truy cập phòng chờ sân bay miễn phí");
        System.out.println("    • Bảo hiểm du lịch quốc tế");
        System.out.println("    • Ưu đãi đặc biệt tại khách sạn & nhà hàng cao cấp");
        System.out.println();

        System.out.println("4️⃣  Thẻ Flash 2IN1");
        System.out.println("    • Kết hợp ATM và Visa trong 1 thẻ");
        System.out.println("    • Thanh toán không tiếp xúc (contactless)");
        System.out.println("    • Hoàn tiền 1% cho giao dịch quốc tế");
        System.out.println();

        int step = 1; // bước đầu tiên
        String cardType = null, ownerName = null, identityNumber = null, phoneNumber = null;
        String password = null, confirmPassword, pin = null, confirmPin;


        while (true) {
            switch (step) {
                case 1:
                    System.out.println("\n--- CHỌN LOẠI THẺ ---");
                    System.out.println("1️⃣  Thẻ ATM Smart 24/7");
                    System.out.println("2️⃣  Thẻ TPBank Visa CashFree");
                    System.out.println("3️⃣  Thẻ TPBank Visa Platinum");
                    System.out.println("4️⃣  Thẻ Flash 2IN1");

                    int cardChoice = menu.inputInt("Chọn loại thẻ (1-4, hoặc -1 để quay lại): ");
                    if (cardChoice == -1) return; // vì đây là bước đầu tiên
                    cardType = switch (cardChoice) {
                        case 1 -> "ATM Smart 24/7";
                        case 2 -> "TPBank Visa CashFree";
                        case 3 -> "TPBank Visa Platinum";
                        case 4 -> "Flash 2IN1";
                        default -> null;
                    };
                    if (cardType == null) {
                        System.out.println("❌ Lựa chọn không hợp lệ!");
                    } else {
                        System.out.println("✅ Bạn đã chọn: " + cardType);
                        step++;
                    }
                    break;

                case 2:
                    ownerName = menu.input("Nhập tên chủ thẻ (hoặc -1 để quay lại): ");
                    if (ownerName.equals("-1")) { step--; break; }
                    identityNumber = menu.input("Nhập số CCCD/CMND (hoặc -1 để quay lại): ");
                    if (identityNumber.equals("-1")) { step--; break; }
                    phoneNumber = menu.input("Nhập số điện thoại (hoặc -1 để quay lại): ");
                    if (phoneNumber.equals("-1")) { step--; break; }
                    step++;
                    break;

                case 3:
                    String otp = menu.input("Nhập mã OTP (hoặc -1 để quay lại): ");
                    if (otp.equals("-1")) { step--; break; }
                    System.out.println("✅ Xác thực OTP thành công!");
                    step++;
                    break;

                case 4:
                    if (menu.input("Đặt ngón trỏ vào máy quét (Enter hoặc -1 để quay lại): ").equals("-1")) { step--; break; }
                    System.out.println("✅ Vân tay hợp lệ!");
                    if (menu.input("Nhìn vào camera (Enter hoặc -1 để quay lại): ").equals("-1")) { step--; break; }
                    System.out.println("✅ Nhận diện khuôn mặt thành công!");
                    step++;
                    break;

                case 5:
                    password = menu.input("Tạo mật khẩu (>=6 ký tự, hoặc -1 để quay lại): ");
                    if (password.equals("-1")) { step--; break; }
                    if (password.length() < 6) {
                        System.out.println("❌ Mật khẩu quá ngắn!");
                        break;
                    }
                    confirmPassword = menu.input("Xác nhận mật khẩu (hoặc -1 để quay lại): ");
                    if (confirmPassword.equals("-1")) { step--; break; }
                    if (!password.equals(confirmPassword)) {
                        System.out.println("❌ Mật khẩu không khớp!");
                        break;
                    }
                    step++;
                    break;

                case 6:
                    pin = menu.input("Nhập PIN (4 số, hoặc -1 để quay lại): ");
                    if (pin.equals("-1")) { step--; break; }
                    if (!pin.matches("\\d{4}")) {
                        System.out.println("❌ PIN không hợp lệ!");
                        break;
                    }
                    confirmPin = menu.input("Xác nhận PIN (hoặc -1 để quay lại): ");
                    if (confirmPin.equals("-1")) { step--; break; }
                    if (!pin.equals(confirmPin)) {
                        System.out.println("❌ PIN không khớp!");
                        break;
                    }
                    step++;
                    break;

                case 7:
                    if (menu.input("Ký tên xác nhận (Enter hoặc -1 để quay lại): ").equals("-1")) { step--; break; }

                    System.out.print("⏳ Đang tạo tài khoản");
                    for (int i = 0; i < 3; i++) {
                        try {
                            Thread.sleep(400);
                            System.out.print(".");
                        } catch (InterruptedException ignored) {}
                    }
                    System.out.println();

                    Account newAcc = accService.createNewAccount(ownerName, password, pin, "TPBank", cardType);
                    if (newAcc != null) {
                        System.out.println("\n🎉 TẠO THẺ THÀNH CÔNG 🎉");
                        System.out.println("👤 Chủ thẻ: " + newAcc.getOwnerName());
                        System.out.println("💳 STK: " + newAcc.getAccountNumber());
                        System.out.println("🏦 Ngân hàng: TPBank");
                        System.out.println("💠 Loại thẻ: " + cardType);
                    } else {
                        System.out.println("❌ Lỗi khi tạo tài khoản!");
                    }
                    return; // kết thúc khi tạo xong
            }
        }
    }
    // ======================== LOGIN ========================
    private void handleLogin() {
        String accNum = menu.input("Nhập số tài khoản (hoặc -1 để quay lại): ");
        if (accNum.equals("-1")) return;

        Account acc = accService.findAccount(accNum);
        if (acc == null) {
            System.out.println("❌ Không tìm thấy tài khoản!");
            return;
        }

        if (acc.isLocked()) {
            System.out.println("🚫 Tài khoản bị khóa!");
            return;
        }

        int attempts = 0;
        while (attempts < 5) {
            String password = menu.input("Nhập mật khẩu (hoặc -1 để quay lại): ");
            if (password.equals("-1")) return;

            currentAcc = accService.login(accNum, password);
            if (currentAcc != null) {
                System.out.println("✅ Đăng nhập thành công!");
                handleAtmMenu();
                return;
            } else {
                attempts++;
                System.out.println("❌ Sai mật khẩu (" + (5 - attempts) + " lần còn lại).");
            }
        }
        accService.lockAccount(accNum);
        System.out.println("🚫 Tài khoản bị khóa do nhập sai quá nhiều lần!");
    }

    // ======================== ATM MENU ========================
    private void handleAtmMenu() {
        while (true) {
            int choice = menu.atmMenu();
            switch (choice) {
                case 1 -> accView.showBalance(currentAcc);

                case 2 -> {
                    double amt = menu.inputDouble("Nhập số tiền nạp (-1 để quay lại): ");
                    if (amt == -1) break;
                    if (!confirmPin()) return;
                    accService.deposit(currentAcc, amt);
                }

                case 3 -> {
                    double amt = menu.inputDouble("Nhập số tiền rút (-1 để quay lại): ");
                    if (amt == -1) break;
                    if (!confirmPin()) return;
                    accService.withdraw(currentAcc, amt);
                }

                case 4 -> handleTransfer();

                case 5 -> {
                    List<model.Transaction> list =
                            transService.getTransactionsByAccount(currentAcc.getAccountNumber());
                    transView.showAll(list);
                }

                case 6 -> {
                    String oldPin = menu.input("Nhập PIN cũ (-1 để quay lại): ");
                    if (oldPin.equals("-1")) break;
                    String newPin = menu.input("Nhập PIN mới (-1 để quay lại): ");
                    if (newPin.equals("-1")) break;
                    accService.changePin(currentAcc, oldPin, newPin);
                }

                case 0 -> { logout(); return; }

                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private void handleTransfer() {
        System.out.println("\n--- CHUYỂN TIỀN ---");
        int type = menu.inputInt("1: Cùng ngân hàng, 2: Khác ngân hàng (-1 để quay lại): ");
        if (type == -1) return;

        String targetAccNum = menu.input("Nhập số tài khoản nhận (-1 để quay lại): ");
        if (targetAccNum.equals("-1")) return;

        Account targetAcc = accRepo.findByAccountNumber(targetAccNum);
        if (targetAcc == null) {
            System.out.println("❌ Không tìm thấy tài khoản nhận!");
            return;
        }

        double amount = menu.inputDouble("Nhập số tiền (-1 để quay lại): ");
        if (amount == -1) return;

        if (!confirmPin()) return;

        boolean success = accService.transfer(currentAcc, targetAccNum, amount);
        if (success)
            System.out.println("✅ Chuyển tiền thành công!");
        else
            System.out.println("❌ Giao dịch thất bại!");
    }

    // ======================== CONFIRM PIN ========================
    private boolean confirmPin() {
        int attempts = 0;
        while (attempts < 5) {
            String pin = menu.input("Nhập PIN để xác nhận (-1 để quay lại): ");
            if (pin.equals("-1")) return false;
            if (accService.verifyPin(currentAcc, pin))
                return true;
            else {
                attempts++;
                System.out.println("❌ PIN sai (" + (5 - attempts) + " lần còn lại).");
            }
        }
        currentAcc.setLocked(true);
        accRepo.saveAll();
        System.out.println("🚫 Tài khoản bị khóa do nhập sai PIN quá nhiều lần!");
        return false;
    }

    // ======================== LOGOUT ========================
    private void logout() {
        currentAcc = null;
        System.out.println("👋 Đã đăng xuất khỏi hệ thống.");
    }
}