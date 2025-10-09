

import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        double exchangeRate = 23000;
        double usd;
        Scanner input = new Scanner(System.in);

        do {
            System.out.print("Nhập số tiền cần chuyển đổi (USD): ");
            usd = input.nextDouble();

            if (usd < 0) {
                System.out.println("Số tiền không hợp lệ. Vui lòng nhập lại (số tiền phải lớn hơn 0) ");
            }
        } while (usd < 0);

        double converted = usd * exchangeRate;
        System.out.println(usd + "(USD) = " + converted + "(VND)");
    }
}
