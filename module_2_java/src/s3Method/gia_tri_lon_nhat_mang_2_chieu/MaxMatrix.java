package s3Method.gia_tri_lon_nhat_mang_2_chieu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MaxMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows, cols;

        while (true) {
            try {
                System.out.print("Nhập số hàng: ");
                rows = sc.nextInt();
                if (rows <= 0) {
                    System.out.println("Số hàng phải lớn hơn 0\n");
                    continue;
                }

                System.out.print("Nhập số cột: ");
                cols = sc.nextInt();
                if (cols <= 0) {
                    System.out.println(" Số cột phải lớn hơn 0\n");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println(" Vui lòng nhập số nguyên dương\n");
                sc.nextLine();
            }
        }

        double[][] matrix = new double[rows][cols];

        System.out.println("\nNhập các phần tử của ma trận:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                while (true) {
                    try {
                        System.out.print("Phần tử [" + i + "][" + j + "]: ");
                        matrix[i][j] = sc.nextDouble();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Giá trị không hợp lệ. Vui lòng nhập số thực\n");
                        sc.nextLine();
                    }
                }
            }
        }

        double max = matrix[0][0];
        int rowMax = 0;
        int colMax = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                    rowMax = i;
                    colMax = j;
                }
            }
        }

        System.out.println("\n Phần tử lớn nhất là: " + max);
        System.out.println("Tọa độ: [" + rowMax + "][" + colMax + "]");
    }
}
