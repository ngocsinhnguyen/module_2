

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHelper2 {

    private static final Scanner sc = new Scanner(System.in);

    // Hàm nhập số nguyên dương (có thông báo tuỳ chỉnh)
    public static int inputPositiveInt(String message) {
        int number;

        while (true) {
            try {
                System.out.print(message);
                number = sc.nextInt();

                if (number <= 0) {
                    System.out.println("Vui lòng nhập số lớn hơn 0.\n");
                    continue;
                }
                return number; // hợp lệ thì trả về
            } catch (InputMismatchException e) {
                System.out.println("Không hợp lệ. Hãy nhập số nguyên.\n");
                sc.nextLine(); // xoá bộ nhớ đệm
            }
        }
    }
}
