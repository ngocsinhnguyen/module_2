package hien_thi_cac_loai_hinh;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Print the rectangle");
            System.out.println("2. Print the square triangle");
            System.out.println("3. Print isosceles triangle");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    printRectangle(sc);
                    break;
                case 2:
                    printSquareTriangle(sc);
                    break;
                case 3:
                    printIsoscelesTriangle(sc);
                    break;
                case 4:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);
    }


    public static void printRectangle(Scanner sc) {
        System.out.print("Enter width: ");
        int width = sc.nextInt();
        System.out.print("Enter height: ");
        int height = sc.nextInt();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }


    public static void printSquareTriangle(Scanner sc) {
        System.out.println("1. Bottom-left");
        System.out.println("2. Top-left");
        System.out.println("3. Bottom-right");
        System.out.println("4. Top-right");
        System.out.print("Choose corner type: ");
        int type = sc.nextInt();

        System.out.print("Enter height: ");
        int height = sc.nextInt();

        switch (type) {
            case 1:
                for (int i = 1; i <= height; i++) {
                    for (int j = 1; j <= i; j++) {
                        System.out.print("* ");
                    }
                    System.out.println();
                }
                break;

            case 2:
                for (int i = height; i >= 1; i--) {
                    for (int j = 1; j <= i; j++) {
                        System.out.print("* ");
                    }
                    System.out.println();
                }
                break;

            case 3:
                for (int i = 1; i <= height; i++) {
                    for (int j = 1; j <= height - i; j++) {
                        System.out.print("  ");
                    }
                    for (int j = 1; j <= i; j++) {
                        System.out.print("* ");
                    }
                    System.out.println();
                }
                break;

            case 4:
                for (int i = height; i >= 1; i--) {
                    for (int j = 1; j <= height - i; j++) {
                        System.out.print("  ");
                    }
                    for (int j = 1; j <= i; j++) {
                        System.out.print("* ");
                    }
                    System.out.println();
                }
                break;

            default:
                System.out.println("Invalid type!");
        }
    }


    public static void printIsoscelesTriangle(Scanner sc) {
        System.out.print("Enter height: ");
        int height = sc.nextInt();

        for (int i = 1; i <= height; i++) {
            for (int j = i; j < height; j++) {
                System.out.print("  ");
            }
            for (int j = 1; j <= (2 * i - 1); j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}
