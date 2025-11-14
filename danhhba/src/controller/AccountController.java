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
        System.out.println("║     TẠO THẺ TPBANK MỚI TẠI LIVEBANK      ║");
        System.out.println("╚════════════════════════════════════════╝");

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

        int step = 1;
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
                    if (cardChoice == -1) return;
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
                    while (true) {
                        ownerName = menu.input("Nhập tên chủ thẻ (hoặc -1 để quay lại): ").trim();
                        if (ownerName.equals("-1")) { step--; break; }
                        if (!ownerName.matches("[\\p{L} ]+")) {
                            System.out.println("❌ Tên chỉ được chứa chữ (không có số hoặc ký tự đặc biệt)!");
                            continue;
                        }
                        break;
                    }
                    if (step < 2) break;

                    while (true) {
                        identityNumber = menu.input("Nhập số CCCD/CMND (hoặc -1 để quay lại): ").trim();
                        if (identityNumber.equals("-1")) { step--; break; }
                        if (!identityNumber.matches("\\d{12}")) {
                            System.out.println("❌ CCCD/CMND không hợp lệ! Phải gồm đúng 12 chữ số.");
                            continue;
                        }
                        break;
                    }
                    if (step < 2) break;

                    while (true) {
                        phoneNumber = menu.input("Nhập số điện thoại (hoặc -1 để quay lại): ").trim();
                        if (phoneNumber.equals("-1")) { step--; break; }
                        if (!phoneNumber.matches("\\d{10}")) {
                            System.out.println("❌ Số điện thoại không hợp lệ! Phải gồm đúng 10 chữ số.");
                            continue;
                        }
                        break;
                    }
                    if (step < 2) break;

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
                    return;
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

                case 2 -> handleDeposit();

                case 3 -> handleWithdraw();

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

    // ======================== DEPOSIT ========================
    private void handleDeposit() {
        while (true) {
            double amt = getValidAmount("Nhập số tiền nạp (-1 để quay lại): ");
            if (amt == -1) return;

            if (!confirmPin()) continue; // Nếu PIN sai, quay lại nhập lại số tiền

            accService.deposit(currentAcc, amt);
            System.out.println("\n✅ NẠP TIỀN THÀNH CÔNG!");
            System.out.println("💰 Số tiền nạp: " + String.format("%,.0f", amt) + " VND");
            System.out.println("💳 Số dư hiện tại: " + String.format("%,.0f", currentAcc.getBalance()) + " VND");
            System.out.println();
            break;
        }
    }

    // ======================== WITHDRAW ========================
    private void handleWithdraw() {
        while (true) {
            double amt = getValidAmount("Nhập số tiền rút (-1 để quay lại): ");
            if (amt == -1) return;

            if (!confirmPin()) continue; // Nếu PIN sai, quay lại nhập lại số tiền

            accService.withdraw(currentAcc, amt);
            System.out.println("\n✅ RÚT TIỀN THÀNH CÔNG!");
            System.out.println("💰 Số tiền rút: " + String.format("%,.0f", amt) + " VND");
            System.out.println("💳 Số dư hiện tại: " + String.format("%,.0f", currentAcc.getBalance()) + " VND");
            System.out.println();
            break;
        }
    }

    // ======================== TRANSFER ========================
    private void handleTransfer() {
        System.out.println("\n--- CHUYỂN TIỀN ---");

        while (true) {
            // Bước 1: Chọn loại chuyển tiền
            String receiverBank = selectTransferType();
            if (receiverBank == null) return;

            while (true) {
                // Bước 2: Nhập và tìm tài khoản nhận
                Account targetAcc = selectReceiverAccount(receiverBank);
                if (targetAcc == null) break; // Quay lại chọn loại chuyển tiền

                while (true) {
                    // Bước 3: Nhập số tiền
                    double amount = menu.inputDouble("Nhập số tiền (-1 để quay lại): ");
                    if (amount == -1) break; // Quay lại nhập tài khoản nhận

                    // Bước 4: Xác nhận PIN
                    if (!confirmPin()) continue; // PIN sai, nhập lại số tiền

                    // Bước 5: Thực hiện giao dịch
                    String targetAccNum = targetAcc.getAccountNumber();
                    boolean success = accService.transfer(currentAcc, targetAccNum, receiverBank, amount);

                    if (success) {
                        System.out.println("\n🎉 CHUYỂN TIỀN THÀNH CÔNG!");
                        System.out.println("📤 Người gửi: " + currentAcc.getOwnerName());
                        System.out.println("📥 Người nhận: " + targetAcc.getOwnerName());
                        System.out.println("🏦 Ngân hàng nhận: " + receiverBank);
                        System.out.println("💰 Số tiền chuyển: " + String.format("%,.0f", amount) + " VND");
                        System.out.println("💳 Số dư hiện tại: " + String.format("%,.0f", currentAcc.getBalance()) + " VND");
                        System.out.println();
                    } else {
                        System.out.println("\n❌ Giao dịch thất bại! Vui lòng kiểm tra lại số dư hoặc thông tin.");
                    }
                    return;
                }
            }
        }
    }

    // ======================== SELECT TRANSFER TYPE ========================
    private String selectTransferType() {
        while (true) {
            System.out.println("------------------------------------");
            int type = menu.inputInt("1: Cùng ngân hàng (TPBank), 2: Khác ngân hàng (-1 để quay lại): ");
            if (type == -1) {
                return null;
            }
            if (type == 1) {
                return "TPBank";
            } else if (type == 2) {
                String bank = selectBank();
                if (bank == null) continue;
                return bank;
            } else {
                System.out.println("❌ Lựa chọn không hợp lệ! Vui lòng chọn lại.");
            }
        }
    }

    // ======================== SELECT RECEIVER ACCOUNT ========================
    private Account selectReceiverAccount(String receiverBank) {
        while (true) {
            String targetAccNum = menu.input("Nhập số tài khoản nhận (-1 để quay lại): ");
            if (targetAccNum.equals("-1")) {
                return null;
            }

            System.out.print("⏳ Đang tìm kiếm tài khoản...");
            Account targetAcc = accRepo.findByAccountNumberAndBank(targetAccNum, receiverBank);
            System.out.println();

            if (targetAcc == null) {
                System.out.println("❌ KHÔNG TÌM THẤY tài khoản nhận khớp với STK và Ngân hàng đã nhập.");
                System.out.println("Vui lòng thử lại.");
            } else {
                System.out.println("✅ Đã tìm thấy tài khoản nhận: " + targetAcc.getOwnerName());
                return targetAcc;
            }
        }
    }

    // ======================== SELECT BANK ========================
    private String selectBank() {
        String[] banks = {
                "Vietcombank (VCB)",
                "Agribank (AGB)",
                "BIDV (BIDV)",
                "Techcombank (TCB)",
                "VietinBank (CTG)",
                "MB Bank (MBB)",
                "ACB Bank (ACB)",
                "SHB (SHB)",
                "STB Bank (STB)",
                "SeABank (SEA)"
        };

        String[] bankCodes = {
                "Vietcombank",
                "Agribank",
                "BIDV",
                "Techcombank",
                "VietinBank",
                "MBBank",
                "ACBBank",
                "SHBank",
                "STBank",
                "SeABank"
        };

        System.out.println("\n--- DANH SÁCH NGÂN HÀNG ---");
        for (int i = 0; i < banks.length; i++) {
            System.out.println((i + 1) + ". " + banks[i]);
        }

        while (true) {
            int choice = menu.inputInt("Chọn ngân hàng (-1 để quay lại): ");

            if (choice == -1) {
                return null;
            } else if (choice > 0 && choice <= banks.length) {
                System.out.println("✅ Bạn đã chọn: " + banks[choice - 1]);
                return bankCodes[choice - 1];
            } else {
                System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        }
    }

    // ======================== VALIDATE AMOUNT ========================
    private double getValidAmount(String prompt) {
        final double MIN_AMOUNT = 50000;
        final double MAX_AMOUNT = 5000000;
        final double DIVISOR = 50000;

        while (true) {
            try {
                double amt = menu.inputDouble(prompt);
                if (amt == -1) return -1;

                if (amt < MIN_AMOUNT) {
                    System.out.println("❌ Số tiền tối thiểu là " + String.format("%,.0f", MIN_AMOUNT) + " VND!");
                    continue;
                }

                if (amt > MAX_AMOUNT) {
                    System.out.println("❌ Số tiền tối đa là " + String.format("%,.0f", MAX_AMOUNT) + " VND!");
                    continue;
                }

                if (amt % DIVISOR != 0) {
                    System.out.println("❌ Số tiền phải chia hết cho " + String.format("%,.0f", DIVISOR) + " VND!");
                    continue;
                }

                return amt;
            } catch (Exception e) {
                System.out.println("⚠️ Giá trị không hợp lệ. Vui lòng thử lại!");
            }
        }
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