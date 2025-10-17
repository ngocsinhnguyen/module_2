package s7_abstract_interface.interface_colorable;


public class TestColorable {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[4];
        shapes[0] = new Circle(3.0);
        shapes[1] = new Rectangle(3.0, 5.0);
        shapes[2] = new Square(4.0);
        shapes[3] = new Square(7.5, "red", true);

        for (Shape shape : shapes) {
            System.out.println(shape + " | Diện tích: " + getArea(shape));
            if (shape instanceof Colorable) {
                ((Colorable) shape).howToColor();
            }
        }
    }

    private static double getArea(Shape shape) {
        if (shape instanceof Circle) {
            return ((Circle) shape).getArea();
        } else if (shape instanceof Rectangle) {
            return ((Rectangle) shape).getArea();
        }
        return 0;
    }
}

