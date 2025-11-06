package view;

import java.util.Scanner;

public class MenuView {
    private final Scanner sc = new Scanner(System.in);

    public int mainMenu() {
        System.out.println("=== ATM SYSTEM ===");
        System.out.println("1. Đăng nhập");
        System.out.println("0. Thoát");
        System.out.print("Chọn: ");
        return sc.nextInt();
    }

    public int atmMenu() {
        System.out.println("=== MENU ===");
        System.out.println("1. Số dư");
        System.out.println("2. Gửi tiền");
        System.out.println("3. Rút tiền");
        System.out.println("4. Chuyển tiền");
        System.out.println("5. Lịch sử giao dịch");
        System.out.println("0. Logout");
        System.out.print("Chọn: ");
        return sc.nextInt();
    }

    public String input(String msg) {
        System.out.print(msg);
        return sc.next();
    }

    public double inputDouble(String msg) {
        System.out.print(msg);
        return sc.nextDouble();
    }
}

