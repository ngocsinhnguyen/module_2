package s2LoopAndArray.tim_gia_tri_trong_mang;

import java.util.Scanner;

public class StudentArray {
    public static void main(String[] args) {
        String[] students = {"Christian", "Michael", "Daniel", "Camila", "Sienna", "Tanya", "Connor", "Zachariah"};
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a student's name: ");
        String input_name = scanner.nextLine();

        boolean isExist = false; //

        for (int i = 0; i < students.length; i++) {
            if (students[i].equalsIgnoreCase(input_name)) { // ✅ dùng equalsIgnoreCase để không phân biệt hoa thường
                System.out.println("Position of the student \"" + input_name + "\" is: " + i);
                isExist = true;
                break;
            }
        }

        if (!isExist) {
            System.out.println("Student \"" + input_name + "\" not found in the list.");
        }
    }
}
