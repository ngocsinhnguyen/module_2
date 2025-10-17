package s7_abstract_interface.interface_resizeable;

import java.util.Random;

public class TestResizeable {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[3];
        shapes[0] = new Circle(5.0);
        shapes[1] = new Rectangle(3.0, 4.0);
        shapes[2] = new Square(6.0);

        Random random = new Random();

        for (Shape shape : shapes) {
            System.out.println("Trước khi thay đổi: " + shape);

            double percent = 1 + random.nextInt(100);
            if (shape instanceof Resizeable) {
                ((Resizeable) shape).resize(percent);
            }

            System.out.println("Sau khi tăng " + percent + "%: " + shape);
        }
    }
}
