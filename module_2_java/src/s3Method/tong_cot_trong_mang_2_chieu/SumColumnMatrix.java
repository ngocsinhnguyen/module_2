package s3Method.tong_cot_trong_mang_2_chieu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SumColumnMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows, cols = 0;

        while (true) {
            try {
                System.out.print("Nhập số hàng của ma trận: ");
                rows = sc.nextInt();
                if (rows <= 0) {
                    System.out.println("Số hàng phải lớn hơn 0\n");
                    continue;
                }

                System.out.print("Nhập số cột của ma trận: ");
                cols = sc.nextInt();
                if (cols <= 0) {
                    System.out.println("Số cột phải lớn hơn 0\n");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Không hợp lệ. Vui lòng nhập lại\n");
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
                        System.out.println("Không hợp lệ! Vui lòng nhập lại\n");
                        sc.nextLine();
                    }
                }
            }
        }

        int colIndex;
        while (true) {
            try {
                System.out.print("\nNhập chỉ số cột cần tính tổng: ");
                colIndex = sc.nextInt();
                if (colIndex < 0 || colIndex >= cols) {
                    System.out.println("Cột phải nằm trong khoảng từ 0 đến " + (cols - 1) + ".\n");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Không hợp lê. Vui lòng nhập lại\n");
                sc.nextLine();
            }
        }

        double sum = 0;
        for (int i = 0; i < rows; i++) {
            sum += matrix[i][colIndex];
        }

        System.out.println("\n Tổng các phần tử ở cột " + colIndex + " là: " + sum);
    }
}
