package phuong_trinh_bac_22;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input{
    private static final Scanner sc = new Scanner(System.in);

    // Hàm nhập 1 số thực (double)
    public static double inputDouble(String message) {
        double number;
        while (true) {
            try {
                System.out.print(message);
                number = sc.nextDouble();
                return number; // hợp lệ thì trả về
            } catch (InputMismatchException e) {
                System.out.println(" Không hợp lệ. Hãy nhập một số thực.\n");
                sc.nextLine(); // xóa bộ nhớ đệm
            }
        }
    }
}
