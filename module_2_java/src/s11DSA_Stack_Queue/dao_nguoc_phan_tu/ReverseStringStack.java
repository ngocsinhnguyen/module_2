package s11DSA_Stack_Queue.dao_nguoc_phan_tu;

import java.util.Stack;

public class ReverseStringStack {
    public static String reverseWords(String sentence) {
        Stack<String> stack = new Stack<>();
        String[] words = sentence.split(" ");

        for (String word : words) {
            stack.push(word);
        }

        StringBuilder reversed = new StringBuilder();
        while (!stack.isEmpty()) {
            reversed.append(stack.pop()).append(" ");
        }

        return reversed.toString().trim();
    }

    public static void main(String[] args) {
        String str = "abcd xyz";
        String reversed = reverseWords(str);
        System.out.println("Chuỗi sau khi đảo ngược: " + reversed);
    }
}
