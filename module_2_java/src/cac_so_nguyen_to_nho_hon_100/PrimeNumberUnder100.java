package cac_so_nguyen_to_nho_hon_100;

import hien_thi_20_so_nguyen_to_dau_tien.PrimeNumber;

public class PrimeNumberUnder100 {
    public static void main(String[] args) {
        System.out.println("Các số nguyên tố nhỏ hơn 100 là:");

        for (int i = 2; i < 100; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
            }
        }
    }

    public static boolean isPrime(int n) {
        return PrimeNumber.isPrime(n);
    }
}
