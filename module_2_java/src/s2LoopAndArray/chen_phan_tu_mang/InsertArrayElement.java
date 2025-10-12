package s2LoopAndArray.chen_phan_tu_mang;

import java.util.Scanner;

public class InsertArrayElement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập số phần tử của mảng: ");
        int n = sc.nextInt();
        int[] arr = new int[n + 1];

        System.out.println("Nhập các phần tử của mảng:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Nhập giá trị X cần chèn: ");
        int X = sc.nextInt();

        System.out.print("Nhập vị trí cần chèn (0 -> " + n + "): ");
        int index = sc.nextInt();

        if (index < 0 || index > n) {
            System.out.println("Không thể chèn phần tử vào vị trí này!");
        } else {
            for (int i = n; i > index; i--) {
                arr[i] = arr[i - 1];
            }

            arr[index] = X;

            System.out.println("Mảng sau khi chèn:");
            for (int i = 0; i <= n; i++) {
                System.out.print(arr[i] + " ");
            }
        }
    }
}
