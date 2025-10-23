package s10dsa.LinkedList;

public class MyLinkedListTest {
    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();

        list.addFirst("Hello");
        list.addLast("World");
        list.add(1, "Java");
        list.addLast("LinkedList");

        System.out.println("Danh sách hiện tại:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println("\nPhần tử đầu: " + list.getFirst());
        System.out.println("Phần tử cuối: " + list.getLast());
        System.out.println("Có chứa 'Java' không? " + list.contains("Java"));
        System.out.println("Vị trí của 'World': " + list.indexOf("World"));

        System.out.println("\nXóa phần tử tại vị trí 1: " + list.remove(1));
        System.out.println("Sau khi xóa:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        MyLinkedList<String> copy = list.clone();
        System.out.println("\nDanh sách sao chép:");
        for (int i = 0; i < copy.size(); i++) {
            System.out.println(copy.get(i));
        }
    }
}
