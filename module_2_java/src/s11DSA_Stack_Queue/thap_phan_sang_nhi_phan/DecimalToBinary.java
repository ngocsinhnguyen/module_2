package s11DSA_Stack_Queue.thap_phan_sang_nhi_phan;

import java.util.Stack;

public class DecimalToBinary {
    public static String convertToBinary(int decimal) {
        Stack<Integer> stack = new Stack<>();

        int n = decimal;
        while (n > 0) {
            int remainder = n % 2;
            stack.push(remainder);
            n = n / 2;
        }

        StringBuilder binary = new StringBuilder();
        while (!stack.isEmpty()) {
            binary.append(stack.pop());
        }

        return binary.toString();
    }

    public static void main(String[] args) {
        int decimalNumber = 30;
        String binary = convertToBinary(decimalNumber);
        System.out.println("Số " + decimalNumber + " trong hệ nhị phân là: " + binary);
    }
}
