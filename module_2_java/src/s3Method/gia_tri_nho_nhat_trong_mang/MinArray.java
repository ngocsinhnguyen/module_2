package s3Method.gia_tri_nho_nhat_trong_mang;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MinArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = 0;

        while (true) {
            try {
                System.out.print("Nhập số lượng phần tử của mảng: ");
                size = sc.nextInt();

                if (size <= 0) {
                    System.out.println("Số lượng phần tử phải lớn hơn 0. Vui lòng nhập lại.\n");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Không hợp lệ. Vui lòng nhập lại\n");
                sc.nextLine();
            }
        }

        int[] arr = new int[size];

        System.out.println("\nNhập các phần tử của mảng:");
        for (int i = 0; i < size; i++) {
            while (true) {
                try {
                    System.out.print("Phần tử [" + i + "]: ");
                    arr[i] = sc.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Giá trị không hợp lệ! Vui lòng nhập số nguyên.\n");
                    sc.nextLine();
                }
            }
        }

        int min = arr[0];
        for (int i = 1; i < size; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        System.out.println("\n Giá trị nhỏ nhất trong mảng là: " + min);
    }
}
