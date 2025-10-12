package s3Method.dem_ky_tu_trong_chuoi;

import java.util.Scanner;

public class CountCharacter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập chuỗi: ");
        String str = sc.nextLine();

        char cha;
        while (true) {
            System.out.print("Nhập ký tự cần đếm: ");
            String input = sc.nextLine();

            if (input.length() != 1) {
                System.out.println(" Vui lòng nhập 1 ký tự duy nhất\n");
            } else {
                cha = input.charAt(0);
                break;
            }
        }

        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == cha) {
                count++;
            }
        }

        System.out.println("\n Ký tự '" + cha + "' xuất hiện " + count + " lần trong chuỗi.");
    }
}
