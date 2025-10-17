package s7_abstract_interface.interface_resizeable;


public class Square extends Rectangle implements Resizeable {

    public Square() {
    }

    public Square(double side) {
        super(side, side);
    }

    public Square(double side, String color, boolean filled) {
        super(side, side, color, filled);
    }

    public double getSide() {
        return getWidth();
    }

    public void setSide(double side) {
        setWidth(side);
        setLength(side);
    }

    @Override
    public String toString() {
        return "A Square with side=" + getSide() + ", area=" + getArea();
    }

    @Override
    public void resize(double percent) {
        double newSide = getSide() + getSide() * percent / 100;
        setSide(newSide);
    }
}
