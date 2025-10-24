package s11DSA_Stack_Queue.dao_nguoc_phan_tu;

import java.util.Stack;

public class ReverseArrayStack {
    public static void reverseArray(int[] arr) {
        Stack<Integer> stack = new Stack<>();

        for (int num : arr) {
            stack.push(num);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = stack.pop();
        }
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        reverseArray(numbers);
        System.out.print("Mảng sau khi đảo ngược: ");
        for (int n : numbers) {
            System.out.print(n + " ");
        }
    }
}
