import  java.util.Scanner;

public class doi_tien {
    public static void main(String[] args) {
        double vnd = 23000;
        double usd;
        Scanner input = new Scanner(System.in);
        System.out.print("Nhập số tiền USD: ");
        usd = input.nextDouble();
        double quydoi = usd * 23000;
        System.out.println("Giá trị VND: " + quydoi);
    }
}
