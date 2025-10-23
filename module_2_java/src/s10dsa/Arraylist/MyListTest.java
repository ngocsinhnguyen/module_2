package s10dsa.Arraylist;

public class MyListTest {
    public static void main(String[] args) {
        MyList<Integer> list = new MyList<>();

        list.add(10);
        list.add(20);
        list.add(30);
        list.add(1, 15);

        System.out.println("Danh sách hiện tại: " + list);
        System.out.println("Kích thước: " + list.size());

        list.remove(2);
        System.out.println("Sau khi xóa phần tử tại index 2: " + list);

        System.out.println("Phần tử tại index 1: " + list.get(1));
        System.out.println("Chứa 20? " + list.contains(20));
        System.out.println("Index của 30: " + list.indexOf(30));

        MyList<Integer> cloneList = list.clone();
        System.out.println("Bản sao: " + cloneList);

        list.clear();
        System.out.println("Sau khi clear: " + list);
    }
}
