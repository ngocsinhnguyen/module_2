package s5AccessModifier.xay_dung_lop_chi_ghi;

public class Test {
    public static void main(String[] args) {

        Student s = new Student();

        s.setName("Sinh");
        s.setClasses("C08");

        System.out.println("Tên: " + s.getName());
        System.out.println("Lớp: " + s.getClasses());
    }
}
