package s3Method.tong_duong_cheo_chinh;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Matrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size ;

        while (true) {
            try {
                System.out.print("Nhập kích thước của ma trận vuông : ");
                size = sc.nextInt();
                if (size <= 0) {
                    System.out.println("Không hợp lệ! Vui lòng nhập lại\n");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Không hợp lệ! Vui lòng nhập lại\n");
                sc.nextLine();
            }
        }

        double[][] matrix = new double[size][size];

        System.out.println("\nNhập các phần tử của ma trận: ");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
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

        double sumDiagonal = 0;
        for (int i = 0; i < size; i++) {
            sumDiagonal += matrix[i][i];
        }

        System.out.println("\n Ma trận vuông vừa nhập:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("\nTổng các phần tử trên đường chéo chính là: " + sumDiagonal);
    }
}
