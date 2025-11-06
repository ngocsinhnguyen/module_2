package mvc.view;

import java.util.Scanner;

public class MainMenu {

    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chào mừng đã đến với nhà hàng của chúng tôi.");
        while (true) {
            System.out.println("Menu của  chúng tôi bao gồm:");
            System.out.println("1. Menu buffer");
            System.out.println("2. Menu thường");
            System.out.println("0. Kết thúc phục vụ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    MenuBuffer.menuBuffer();
                    break;
                case 2:
                    break;
                case 0:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Chọn đúng menu");
            }
        }
    }
}
