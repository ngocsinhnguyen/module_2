package gop_mang;

import java.util.Scanner;

public class MergeArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập số phần tử của mảng 1: ");
        int n1 = sc.nextInt();
        int[] arr1 = new int[n1];

        System.out.print("Nhập số phần tử của mảng 2: ");
        int n2 = sc.nextInt();
        int[] arr2 = new int[n2];


        System.out.println("Nhập các phần tử cho mảng 1:");
        for (int i = 0; i < n1; i++) {
            arr1[i] = sc.nextInt();
        }

        System.out.println("Nhập các phần tử cho mảng 2:");
        for (int i = 0; i < n2; i++) {
            arr2[i] = sc.nextInt();
        }

        int[] arr3 = new int[n1 + n2];

        for (int i = 0; i < n1; i++) {
            arr3[i] = arr1[i];
        }

        for (int i = 0; i < n2; i++) {
            arr3[n1 + i] = arr2[i];
        }

        System.out.println("Mảng sau khi gộp là:");
        for (int i = 0; i < arr3.length; i++) {
            System.out.print(arr3[i] + " ");
        }
    }
}
