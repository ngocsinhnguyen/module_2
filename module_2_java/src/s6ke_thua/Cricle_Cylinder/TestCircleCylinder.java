package s6ke_thua.Cricle_Cylinder;

public class TestCircleCylinder {
    public static void main(String[] args) {
        Circle c1 = new Circle(3.0, "blue");
        System.out.println(c1.toString());

        Cylinder cy1 = new Cylinder(3.0, "green", 5.0);
        System.out.println(cy1.toString());
    }
}
