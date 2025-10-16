package s6ke_thua.Point2D_Point3D;

public class TestPoint {
    public static void main(String[] args) {

        Point2D p2d = new Point2D(1f, 2f);
        System.out.println("Point2D: " + p2d);
        p2d.setXY(3f, 4f);
        System.out.println("After setXY: " + p2d);


        Point3D p3d = new Point3D(5f, 6f, 7f);
        System.out.println("Point3D: " + p3d);
        p3d.setXYZ(8f, 9f, 10f);
        System.out.println("After setXYZ: " + p3d);
    }
}
