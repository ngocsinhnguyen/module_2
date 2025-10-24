package s11DSA_Stack_Queue.thuc_hanh_trien_khai_stack;

public class GenericStackClient {
    private static void stackOfIsString(){
        MyGenericStack<String> stack =  new MyGenericStack();
        stack.push("Five");
        stack.push("Six");
        stack.push("Seven");
        stack.push("Eight");
        stack.push("Nine");
        System.out.println("1.1 Size of stack after push operations: ");

    }
}
