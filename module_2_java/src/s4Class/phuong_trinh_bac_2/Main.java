package s4Class.phuong_trinh_bac_2;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double a = s4Class.phuong_trinh_bac_2.Input.inputDouble("Nhập a: ");
        double b = s4Class.phuong_trinh_bac_2.Input.inputDouble("Nhập b: ");
        double c = s4Class.phuong_trinh_bac_2.Input.inputDouble("Nhập c: ");

        QuadraticEquation equation = new QuadraticEquation(a, b, c);

        double delta = equation.getDiscriminant();

        if (delta > 0) {
            System.out.println("Phương trình có 2 nghiệm phân biệt:");
            System.out.println("x1 = " + equation.getRoot1());
            System.out.println("x2 = " + equation.getRoot2());
        } else if (delta == 0) {
            System.out.println("Phương trình có nghiệm kép:");
            System.out.println("x = " + equation.getRoot1());
        } else {
            System.out.println("The equation has no roots");
        }

        sc.close();
    }
}
