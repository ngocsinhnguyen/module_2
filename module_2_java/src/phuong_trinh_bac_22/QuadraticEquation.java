package phuong_trinh_bac_22;

public class QuadraticEquation {
    public static void main(String[] args) {
        System.out.println("=== GIẢI PHƯƠNG TRÌNH BẬC 2: ax² + bx + c = 0 ===\n");

        double a = Input.inputDouble("Nhập a: ");
        double b = Input.inputDouble("Nhập b: ");
        double c = Input.inputDouble("Nhập c: ");

        if (a == 0) {
            // Khi a = 0 thì phương trình trở thành bậc 1
            if (b == 0) {
                System.out.println(c == 0 ? "Phương trình có vô số nghiệm." : "Phương trình vô nghiệm.");
            } else {
                double x = -c / b;
                System.out.println("Phương trình bậc nhất có nghiệm x = " + x);
            }
        } else {
            // Phương trình bậc 2
            double delta = Math.pow(b, 2) - 4 * a * c;

            if (delta < 0) {
                System.out.println("Phương trình vô nghiệm.");
            } else if (delta == 0) {
                double x = -b / (2 * a);
                System.out.println("Phương trình có nghiệm kép x = " + x);
            } else {
                double x1 = (-b + Math.pow(delta, 0.5)) / (2 * a);
                double x2 = (-b - Math.pow(delta, 0.5)) / (2 * a);
                System.out.println("Phương trình có 2 nghiệm phân biệt:");
                System.out.println("x₁ = " + x1);
                System.out.println("x₂ = " + x2);
            }
        }
    }
}

