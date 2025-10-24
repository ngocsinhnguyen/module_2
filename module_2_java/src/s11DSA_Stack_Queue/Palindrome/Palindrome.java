package s11DSA_Stack_Queue.Palindrome;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Palindrome {
    public static boolean isPalindrome(String input) {
        Stack<Character> stack = new Stack<>();
        Queue<Character> queue = new LinkedList<>();

        String normalized = input.replaceAll("[^a-zA-Z]", "").toLowerCase();

        for (char c : normalized.toCharArray()) {
            stack.push(c);
            queue.offer(c);
        }

        while (!stack.isEmpty()) {
            if (stack.pop() != queue.remove()) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String str1 = "Able was I ere I saw Elba";
        String str2 = "Hello world";

        System.out.println("\"" + str1 + "\" là Palindrome? " + isPalindrome(str1));
        System.out.println("\"" + str2 + "\" là Palindrome? " + isPalindrome(str2));
    }
}
