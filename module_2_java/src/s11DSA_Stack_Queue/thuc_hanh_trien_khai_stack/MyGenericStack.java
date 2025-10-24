package s11DSA_Stack_Queue.thuc_hanh_trien_khai_stack;

import java.util.EmptyStackException;
import java.util.LinkedList;

import static sun.util.locale.LocaleUtils.isEmpty;

public class MyGenericStack<T> {
    private LinkedList<T> stack;

    public MyGenericStack() {
        stack = new LinkedList();
    }

    //cài đặt phương thức push()
    public void  push (T element){
        stack.addFirst(element);
    }

    //cài đặt phương thức pop()
    public T pop() {
        if (isEmpty()){
            throw new EmptyStackException();
        }
        return stack.removeFirst();
    }

    // cài đặt phương thức size
    public int size(){
        return stack.size();
    }

    //cài dặt phương thức isEmpty()
    public boolean isEmpty(){
        if (stack.size()==0){
            return true;
        }
        return false;
    }
}
