package view;

import java.util.Scanner;

public class MenuView {
    private final Scanner sc = new Scanner(System.in);

    // ✅ Hiển thị menu chính
    public int mainMenu() {
        System.out.println("\n=== ATM ===");
        System.out.println("1. Đăng nhập");
        System.out.println("2. Tạo thẻ mới");
        System.out.println("0. Thoát");
        System.out.print("Chọn: ");
        String s = sc.nextLine().trim();
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return -1;
        }
    }

    // ✅ Menu ATM sau khi đăng nhập
    public int atmMenu() {
        System.out.println("\n--- MENU CHỨC NĂNG ---");
        System.out.println("1. Xem số dư");
        System.out.println("2. Nạp tiền");
        System.out.println("3. Rút tiền");
        System.out.println("4. Chuyển tiền");
        System.out.println("5. Lịch sử giao dịch");
        System.out.println("6. Đổi mã PIN");
        System.out.println("0. Đăng xuất");
        System.out.println("-1. 🔙 Quay về");
        System.out.print("Chọn: ");
        String s = sc.nextLine().trim();
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return -1;
        }
    }

    // ✅ Nhập chuỗi — có thể nhập "back" hoặc "-1" để quay về
    public String input(String msg) {
        System.out.print(msg);
        String s = sc.nextLine().trim();
        if (s.equalsIgnoreCase("back") || s.equals("-1")) {
            return "-1"; // đánh dấu người dùng muốn quay lại
        }
        return s;
    }

    // ✅ Nhập số thực (double)
    public double inputDouble(String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim();

            if (s.equalsIgnoreCase("back") || s.equals("-1")) {
                return -1; // quay về
            }

            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Giá trị không hợp lệ. Vui lòng nhập số!");
            }
        }
    }

    // ✅ Nhập số nguyên (int)
    public int inputInt(String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim();

            if (s.equalsIgnoreCase("back") || s.equals("-1")) {
                return -1;
            }

            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Giá trị không hợp lệ. Vui lòng nhập số nguyên!");
            }
        }
    }
}
