package xoa_phan_tu_mang;

import java.util.Scanner;

public class DeleteArrayElement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập số phần tử của mảng: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("Nhập các phần tử của mảng:");
        for (int i = 0; i < n; i++) {
            System.out.print("arr[" + i + "] = ");
            arr[i] = sc.nextInt();
        }

        System.out.print("Mảng ban đầu: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        System.out.print("Nhập phần tử cần xóa: ");
        int X = sc.nextInt();

        int index_del = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] == X) {
                index_del = i;
                break;
            }
        }

        if (index_del == -1) {
            System.out.println("Phần tử " + X + " không có trong mảng.");
        } else {
            for (int i = index_del; i < n - 1; i++) {
                arr[i] = arr[i + 1];
            }
            arr[n-1] = 0;
            n--;

            System.out.print("Mảng sau khi xóa phần tử " + X + ": ");
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }
}
