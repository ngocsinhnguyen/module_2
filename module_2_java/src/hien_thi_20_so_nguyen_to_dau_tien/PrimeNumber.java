package hien_thi_20_so_nguyen_to_dau_tien;

import java.util.Scanner;

public class PrimeNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numbers;

        while (true) {
            try {
                System.out.print("Nhập số lượng số nguyên tố cần hiển thị: ");
                String input = sc.nextLine().trim();

                numbers = Integer.parseInt(input);

                if (numbers <= 0 ) {
                    System.out.println(" Vui lòng nhập một số nguyên dương lớn hơn 0\n");
                    continue;
                }
                break;

            } catch (NumberFormatException ignored) {
                System.out.println(" Vui lòng nhập một số nguyên dương lớn hơn 0\n");
            }
        }

        int count = 0;
        int N = 2;

        System.out.println("Các số nguyên tố đầu tiên là:");
        while (count < numbers) {
            if (isPrime(N)) {
                System.out.print(N + " ");
                count++;
            }
            N++;
        }
    }

    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}
