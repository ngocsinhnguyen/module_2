package mvc.common.validate;

import java.util.Scanner;

public class Validate {

    public static int inputInteger(String mess) {
        Scanner scanner = new Scanner(System.in);
        int temp;
        while (true) {
            try {
                System.out.print("Nhập "+mess+": ");
                temp = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println(mess+" phải là số nguyên. Vui lòng nhập lại");
            } catch (Exception e) {
                System.out.println("Lỗi không xác định. Vui lòng nhập lại");
            }
        }
        return temp;
    }
}
